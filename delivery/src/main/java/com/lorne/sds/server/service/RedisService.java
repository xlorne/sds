package com.lorne.sds.server.service;

import java.util.Set;

/**
 * create by lorne on 2017/10/13
 */
public interface RedisService {

    Set<String> all(String key);
}
