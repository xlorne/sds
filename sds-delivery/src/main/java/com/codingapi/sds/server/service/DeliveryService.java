package com.codingapi.sds.server.service;

import io.netty.channel.ChannelHandlerContext;

/**
 * create by lorne on 2017/10/13
 */
public interface DeliveryService {

    void delivery(ChannelHandlerContext ctx, Object msg);

    void checkSocket();

    void channelActive(ChannelHandlerContext ctx);

    void channelInactive(ChannelHandlerContext ctx);
}
