package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * create by lorne on 2017/10/13
 */
@Service
public class RedisServiceImpl implements RedisService {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private final static String prefix = "sds_";


    @Override
    public Set<String> all(String key) {
        String mkey = prefix+key;
        return redisTemplate.opsForSet().members(mkey);
    }

}
