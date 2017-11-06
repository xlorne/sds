package com.lorne.sds.server.service;

/**
 * create by lorne on 2017/10/10
 */
public interface SocketService {


    /**
     * 创建管道数据
     * @param uniqueKey 唯一标示
     */
    void create(String uniqueKey);

    /**
     * 删除管道数据
     * @param uniqueKey 唯一标示
     */
    void remove(String uniqueKey);

    /**
     * 获取监听事件对象
     * @return  Socket事件监听
     */
    SocketEventService getSocketEventService();


}
