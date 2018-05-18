package com.codingapi.sds.delivery.service;

import java.util.List;
import java.util.Set;

/**
 * create by lorne on 2017/10/13
 */
public interface RedisService {

    String sds_prefix = "sds_";

    String key_prefix = "skey_";

    String val_prefix = "sval_";


    Set<String> all(String key);

    void removeAll(String key);

    void remove(String key, String uniqueKey);

    void add(String key,String value);

    List<String> models();

    void putKey(String modelName, String uniqueKey, String key);

    String getModelByKey(String key);

    void removeKey(String modelName, String uniqueKey);
}
