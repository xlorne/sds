package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.service.*;
import com.lorne.sds.server.utils.TelnetUtils;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Set;


/**
 * create by lorne on 2017/10/13
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    private DeliveryServerSendEventService deliveryServerSendEventService;

    @Autowired
    private AdminService adminService;


    private Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Override
    public void delivery(ChannelHandlerContext ctx, Object msg) {
        deliveryServerSendEventService.onDeliveryListener(ctx, msg);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        deliveryServerSendEventService.onConnectionListener(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        deliveryServerSendEventService.onDisConnectionListener(ctx);
    }


    private ServiceInstance getInstance(String ipPort) {
        List<ServiceInstance> instances = discoveryClient.getInstances(DeliveryServerService.SOCKET_SERVER_KEY);
        for (ServiceInstance instance : instances) {
            String ip = ((EurekaDiscoveryClient.EurekaServiceInstance) instance).getInstanceInfo().getIPAddr();
            int port = instance.getPort();
            String instancesIpPort = String.format("%s:%d", ip, port);
            if (instancesIpPort.equals(ipPort)) {
                return instance;
            }
        }
        return null;
    }


    @Override
    public void checkSocket() {
        logger.info("check - socket server start ");

        List<String> onlines = adminService.models();

        for (String ipPort : onlines) {
            String ip = ipPort.split(":")[0];
            int port = Integer.parseInt(ipPort.split(":")[1]);

            boolean telnetState = TelnetUtils.telnet(ip, port);

            if (telnetState) {
                ServiceInstance serviceInstance = getInstance(ipPort);
                if (serviceInstance == null) {
                    try {
                        Thread.sleep(1000 * 10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    serviceInstance = getInstance(ipPort);

                }
                logger.info("ipPort -> "+ipPort+" serviceInstance->:" + serviceInstance);

                if (serviceInstance != null) {
                    logger.info("check - server:" + ipPort + " start ");

                    try {
                        String resVal = restTemplate.getForObject(serviceInstance.getUri() + "/socket/index", String.class);
                        if (!"success".equals(resVal)) {
                            redisService.removeAll(ipPort);

                            logger.info("remove -> server:" + ipPort);
                        }
                    }catch (Exception e){
                        logger.error("error--> remove -> server:" + ipPort+", exp->"+e.getMessage());
                        redisService.removeAll(ipPort);

                        continue;
                    }

                    Set<String> values = redisService.all(ipPort);

                    for (String uniqueKey : values) {
                        logger.info("checkChannel -> server:" + ipPort + ",uniqueKey->" + uniqueKey + " start .");
                        try {
                            boolean res = restTemplate.postForObject(serviceInstance.getUri() + "/socket/checkChannel?uniqueKey={uniqueKey}", uniqueKey, Boolean.class, uniqueKey);
                            logger.info("checkChannel -> server:" + ipPort + ",uniqueKey->" + uniqueKey + " end . res->" + res);
                            if (!res) {
                                redisService.remove(ipPort, uniqueKey);
                            }
                        }catch (Exception e){
                            logger.error("error--> remove checkChannel -> server:" + ipPort+", exp->"+e.getMessage());
                            redisService.remove(ipPort, uniqueKey);
                        }
                    }
                }

            } else {
                redisService.removeAll(ipPort);
            }
        }
        logger.info("check - socket server end");
    }
}
