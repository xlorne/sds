package com.lorne.sds.server.service;

import io.netty.channel.ChannelHandlerContext;

/**
 * create by lorne on 2017/10/13
 */
public interface DeliveryServerSendEventService {

    /**
     * 负载均衡分发分配
     * @param ctx   连接对象
     * @param msg   发送的消息数据
     */
    void onDeliveryListener(ChannelHandlerContext ctx, Object msg);


    void onConnectionListener(ChannelHandlerContext ctx);


    void onDisConnectionListener(ChannelHandlerContext ctx);

}
