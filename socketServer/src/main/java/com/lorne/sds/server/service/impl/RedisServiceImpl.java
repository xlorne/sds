package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/10/13
 */
@Service
public class RedisServiceImpl implements RedisService {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private final static String prefix = "sds_";


    @Override
    public void add(String key, String value) {
        String mkey = prefix+key;
        redisTemplate.opsForSet().add(mkey,value);
    }

    @Override
    public void remove(String key, String value) {
        String mkey = prefix+key;
        redisTemplate.opsForSet().remove(mkey,value);
    }
}
