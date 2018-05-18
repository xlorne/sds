package com.codingapi.sds.delivery.service;

import com.codingapi.sds.delivery.model.DeliveryModel;

import java.util.List;

/**
 * create by lorne on 2017/12/6
 */
public interface AdminService {

    List<String> models();

    List<String> connections(String key);

    DeliveryModel getSetting();

}
