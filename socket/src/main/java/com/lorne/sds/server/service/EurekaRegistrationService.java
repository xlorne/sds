package com.lorne.sds.server.service;

/**
 * create by lorne on 2017/10/13
 */
public interface EurekaRegistrationService {

    /**
     * 获取服务的IP和端口
     * @return 格式:ip:port,如127.0.0.1:8080
     */
    String getIpPort();

    /**
     * 获取服务的ServiceId
     * @return 服务的ServiceId
     */
    String getServiceId();

    /**
     * 获取服务的InstanceId
     * @return 服务的InstanceId
     */
    String getInstanceId();


    /**
     * 获取模块名称，也可以直接调用getIpPort()
     * @return 模块名称
     */
    String getModelName();


}
