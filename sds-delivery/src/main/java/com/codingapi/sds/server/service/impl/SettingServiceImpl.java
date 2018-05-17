package com.codingapi.sds.server.service.impl;

import com.codingapi.sds.server.service.RedisService;
import com.codingapi.sds.server.service.SettingService;
import com.codingapi.sds.server.model.DeliveryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create by lorne on 2017/12/6
 */
@Service
public class SettingServiceImpl implements SettingService {


    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RedisService redisService;

    @Autowired
    private Registration registration;


    @Value("${delivery.port}")
    private int deliveryPort;


    @Value("${delivery.check.time}")
    private int maxCheckTime;


    @Override
    public String getDeliveryIp() {
        return registration.getHost();
    }

    @Override
    public int getDeliveryPort() {
        return deliveryPort;
    }


    @Override
    public boolean testRedis() {
        redisService.models();
        return true;
    }

    private boolean isIp(String ipAddress) {
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }


    private List<String> getServices(String key){
        List<String> urls = new ArrayList<>();
        List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(key);
        for (ServiceInstance instanceInfo : serviceInstances) {
            String url = instanceInfo.getUri().toString();
            String address = instanceInfo.getHost();
            if (isIp(address)) {
                urls.add(url);
            }else{
                url = url.replace(address,"127.0.0.1");
                urls.add(url);
            }
        }
        return urls;
    }

    @Override
    public DeliveryModel loadSetting() {
        DeliveryModel model = new DeliveryModel();
        model.setIp(getDeliveryIp());
        model.setPort(getDeliveryPort());
        model.setTestRedis(testRedis());
        model.setDeliverys(getServices("delivery"));
        model.setSockets(getServices("socket-server"));
        return model;
    }

    @Override
    public int getCheckTime() {
        return maxCheckTime;
    }
}
