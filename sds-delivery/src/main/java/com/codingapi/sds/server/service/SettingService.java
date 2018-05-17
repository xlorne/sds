package com.codingapi.sds.server.service;

import com.codingapi.sds.server.model.DeliveryModel;

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
