package com.lorne.sds.server.service;

/**
 * create by lorne on 2017/10/13
 */
public interface RedisService {

    void add(String key,String value);

    void remove(String key,String value);
}
