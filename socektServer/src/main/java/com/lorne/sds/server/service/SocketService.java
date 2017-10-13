package com.lorne.sds.server.service;

/**
 * create by lorne on 2017/10/10
 */
public interface SocketService {


    void create(String uniqueKey);

    void remove(String uniqueKey);

    SocketEventService getSocketEventService();


}
