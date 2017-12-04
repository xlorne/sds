package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.model.Server;
import com.lorne.sds.server.service.DeliveryServerService;
import com.lorne.sds.server.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * create by lorne on 2017/10/14
 */
@Service
public class DeliveryServerServiceImpl implements DeliveryServerService {



    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Server getOkServer() {

        List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(SOCKET_SERVER_KEY);

        //选取socket服务
        if (serviceInstances != null && serviceInstances.size() > 0 ) {
            for(ServiceInstance instance : serviceInstances){
                URI uri = instance.getUri();
                if (uri !=null ) {
                    Server server =  restTemplate.getForObject(uri+"/socket/getServer",Server.class);
                    if(server.getNowCount()<=server.getMaxCount()){
                        return server;
                    }
                }
            }
        }

        return null;
    }

}
