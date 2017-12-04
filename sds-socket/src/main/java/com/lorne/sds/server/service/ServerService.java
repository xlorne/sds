package com.lorne.sds.server.service;

import com.lorne.sds.server.model.Server;

/**
 * create by lorne on 2017/9/21
 */
public interface ServerService {

    /**
     * 获取本地服务配置信息
     * @return  服务数据
     */
    Server getServer();

    /**
     * 发送指令
     * @param uniqueKey 管道唯一标示
     * @param cmd   指令内容
     * @return  是否成功 true成功，false失败
     */
    boolean sendCmd(String uniqueKey, String cmd);

    /**
     * 检查连接
     * @param uniqueKey  管道唯一标示
     * @return  是否有效，true有效，false无效
     */
    boolean checkChannel(String uniqueKey);

}
