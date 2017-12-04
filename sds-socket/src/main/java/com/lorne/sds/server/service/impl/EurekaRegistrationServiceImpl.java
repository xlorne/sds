package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.service.EurekaRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/10/13
 */
@Service
public class EurekaRegistrationServiceImpl implements EurekaRegistrationService {


    @Autowired
    private EurekaRegistration registration;

    @Override
    public String getIpPort() {
        String ipAddress = registration.getInstanceConfig().getIpAddress();
        int port = registration.getNonSecurePort();
        return String.format("%s:%d",ipAddress,port);
    }

    @Override
    public String getServiceId() {
        return registration.getServiceId();
    }

    @Override
    public String getInstanceId() {
        return registration.getInstanceConfig().getInstanceId();
    }

    @Override
    public String getModelName() {
        return getIpPort();
    }
}
