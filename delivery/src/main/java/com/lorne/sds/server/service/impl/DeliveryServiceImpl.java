package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.model.Server;
import com.lorne.sds.server.service.DeliveryServerSendService;
import com.lorne.sds.server.service.DeliveryService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

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
}
