package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.service.EurekaRegistrationService;
import com.lorne.sds.server.service.RedisService;
import com.lorne.sds.server.service.SocketEventService;
import com.lorne.sds.server.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/10/10
 */
@Service
public class SocketServiceImpl implements SocketService {

    @Autowired
    private EurekaRegistrationService eurekaRegistrationService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SocketEventService socketEventService;

    @Override
    public void create(String uniqueKey) {
        String modelName =  eurekaRegistrationService.getIpPort();
        redisService.add(modelName,uniqueKey);
    }

    @Override
    public void remove(String uniqueKey) {
        String modelName =  eurekaRegistrationService.getIpPort();
        redisService.remove(modelName,uniqueKey);
    }

    @Override
    public SocketEventService getSocketEventService() {
        return socketEventService;
    }
}
