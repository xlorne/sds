package com.codingapi.sds.server.service.impl;

import com.codingapi.sds.server.service.SocketControl;
import com.codingapi.sds.server.service.SocketEventService;
import com.codingapi.sds.server.service.SocketService;
import com.codingapi.sds.server.mq.DeliveryClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/10/10
 */
@Service
public class SocketServiceImpl implements SocketService {

    private Logger logger = LoggerFactory.getLogger(SocketServiceImpl.class);

    @Autowired
    private SocketControl eurekaRegistrationService;

    @Autowired
    private DeliveryClient deliveryClient;

    @Autowired
    private SocketEventService socketEventService;

    @Override
    public void create(String uniqueKey) {
        String modelName =  eurekaRegistrationService.getIpPort();
        deliveryClient.add(modelName,uniqueKey);
        logger.info("deliveryClient-add --> modelName:"+modelName+",uniqueKey:"+uniqueKey);
    }

    @Override
    public void remove(String uniqueKey) {
        String modelName =  eurekaRegistrationService.getIpPort();
        deliveryClient.remove(modelName,uniqueKey);

        logger.info("deliveryClient-remove --> modelName:"+modelName+",uniqueKey:"+uniqueKey);
    }

    @Override
    public SocketEventService getSocketEventService() {
        return socketEventService;
    }
}
