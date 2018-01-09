package com.lorne.sds.server.service.impl;

import com.lorne.core.framework.utils.encode.MD5Util;
import com.lorne.sds.server.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * create by lorne on 2017/10/13
 */
@Service
public class RedisServiceImpl implements RedisService {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Override
    public List<String> models() {
        return new ArrayList<>(redisTemplate.keys(sds_prefix + "*"));
    }

    @Override
    public Set<String> all(String key) {
        String mkey = sds_prefix + key;
        return redisTemplate.opsForSet().members(mkey);
    }

    @Override
    public void removeAll(String key) {
        String mkey = sds_prefix + key;
        redisTemplate.delete(mkey);
    }

    @Override
    public void remove(String key, String uniqueKey) {
        String mkey = sds_prefix + key;
        redisTemplate.opsForSet().remove(mkey, uniqueKey);

        removeKey(key, uniqueKey);
    }


    @Override
    public void add(String key, String value) {
        String mkey = sds_prefix + key;
        redisTemplate.opsForSet().add(mkey, value);
    }

    @Override
    public void putKey(String modelName, String uniqueKey, String key) {
        String val = modelName + "#" + uniqueKey;
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key_prefix + key, val);
        operations.set(val_prefix + val, key);
    }

    @Override
    public void removeKey(String modelName, String uniqueKey) {
        String val = modelName + "#" + uniqueKey;
        String mkey = val_prefix + val;
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        String key = operations.get(mkey);
        String mval = operations.get(key_prefix + key);
        if(val.equals(mval)) {
            redisTemplate.delete(key_prefix + key);
        }
        redisTemplate.delete(mkey);
    }

    @Override
    public String getModelByKey(String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.get(key_prefix + key);
    }
}
