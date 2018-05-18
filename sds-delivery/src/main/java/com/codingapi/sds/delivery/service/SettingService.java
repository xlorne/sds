package com.codingapi.sds.delivery.service;

import com.codingapi.sds.delivery.model.DeliveryModel;

/**
 * create by lorne on 2017/12/6
 */
public interface SettingService {

    String getDeliveryIp();

    int getDeliveryPort();

    boolean testRedis();


    DeliveryModel loadSetting();

    int getCheckTime();

}
