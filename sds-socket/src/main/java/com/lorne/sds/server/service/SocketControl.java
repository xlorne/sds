package com.lorne.sds.server.service;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * create by lorne on 2017/10/13
 */
public interface SocketControl {

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


    /**
     * 模块下连接的标示
     * @param channel 管道对象
     * @return uk
     */
    String getUniqueKey(Channel channel);


    /**
     * 模块下连接的标示
     * @param ctx 管道对象
     * @return uk
     */
    String getUniqueKey(ChannelHandlerContext ctx);


    /**
     * 绑定key
     * @param ctx   当前连接对象
     * @param key   key
     */
    void bindKey(ChannelHandlerContext ctx,String key);

    /**
     * 绑定key
     * @param ctx 当前连接对象
     * @param key key
     */
    void bindKey(Channel ctx,String key);

    /**
     * 获取key
     * @param ctx   当前链接对象
     * @return  key
     */
    String getKey(Channel ctx);

    /**
     * 获取key
     * @param ctx   当前链接对象
     * @return  key
     */
    String getKey(ChannelHandlerContext ctx);


    /**
     * 重置心跳时间
     * @param ctx   当前连接对象
     * @param heartTime 心跳时间(秒)
     */
    void resetHeartTime(Channel ctx,int heartTime);



}
