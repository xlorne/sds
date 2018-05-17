package com.codingapi.sds.server.service;

/**
 * Created by yuliang on 2017/4/12.
 */

public interface NettyServerService {


    /**
     * 服务启动
     */
    void start();

    /**
     * 服务关闭
     */
    void close();

}
