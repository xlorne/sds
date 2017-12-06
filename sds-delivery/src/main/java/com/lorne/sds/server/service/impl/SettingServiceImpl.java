package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.model.DeliveryModel;
import com.lorne.sds.server.service.RedisService;
import com.lorne.sds.server.service.SettingService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * create by lorne on 2017/12/6
 */
@Service
public class SettingServiceImpl implements SettingService{

    private Logger logger = LoggerFactory.getLogger(SettingServiceImpl.class);

    @Autowired
    private EurekaRegistration registration;

    @Autowired
    private RedisService redisService;


    @Value("${delivery.port}")
    private int deliveryPort;


    @Autowired
    private EurekaClient eurekaClient;

    public List<InstanceInfo> getConfigServiceInstances(String key) {
        Application application = eurekaClient.getApplication(key);
        if (application == null) {
            logger.error("获取eureka服务失败！");
        }
        return application!=null?application.getInstances():new ArrayList<InstanceInfo>();
    }



    @Override
    public String getDeliveryIp() {
        return registration.getInstanceConfig().getIpAddress();
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
        List<InstanceInfo> instanceInfos =getConfigServiceInstances(key);
        for (InstanceInfo instanceInfo : instanceInfos) {
            String url = instanceInfo.getHomePageUrl();
            String address = instanceInfo.getIPAddr();
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
}
