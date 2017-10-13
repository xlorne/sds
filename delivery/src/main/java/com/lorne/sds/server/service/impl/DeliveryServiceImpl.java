package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.model.Server;
import com.lorne.sds.server.service.DeliveryServerSendService;
import com.lorne.sds.server.service.DeliveryService;
import com.lorne.sds.server.service.RedisService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;


/**
 * create by lorne on 2017/10/13
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final static String SOCKET_SERVER_KEY = "SOCKET-SERVER";


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    private DeliveryServerSendService deliveryServerSendService;


    @Override
    public void delivery(ChannelHandlerContext ctx, Object msg) {
        List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(SOCKET_SERVER_KEY);

        //选取socket服务
        if (serviceInstances != null && serviceInstances.size() > 0 ) {
            for(ServiceInstance instance : serviceInstances){
                URI uri = instance.getUri();
                if (uri !=null ) {
                    Server server =  restTemplate.getForObject(uri+"/socket/getServer",Server.class);
                    if(server.getNowCount()<=server.getMaxCount()){
                        deliveryServerSendService.delivery(ctx,server);
                    }
                }
            }
        }
    }

    @Override
    public void checkSocket() {
        System.out.println("foreach");
        List<ServiceInstance> instances =  discoveryClient.getInstances(SOCKET_SERVER_KEY);
        for(ServiceInstance instance:instances){

            String ip = instance.getHost();
            int port = instance.getPort();
            String ipPort = String.format("%s:%d",ip,port);

            String resVal = restTemplate.getForObject(instance.getUri()+"/socket/index",String.class);

            if(!"success".equals(resVal)){
                redisService.removeAll(ipPort);
            }

            Set<String> values = redisService.all(ipPort);

            for(String uniqueKey:values){
                boolean res =  restTemplate.getForObject(instance.getUri()+"/socket/checkChannel?uniqueKey={uniqueKey}",Boolean.class,uniqueKey);
                if(!res){
                    redisService.remove(ipPort,uniqueKey);
                }
            }

        }
    }
}
