package com.lorne.sds.server.service;

import io.netty.channel.ChannelHandlerContext; /**
 * create by lorne on 2017/10/13
 */
public interface SocketEventService {

    void channelRead(ChannelHandlerContext ctx, String uniqueKey, Object msg);

    void channelActive(ChannelHandlerContext ctx, String uniqueKey);

    void channelInactive(ChannelHandlerContext ctx, String uniqueKey);

    void heartNoWriteData(ChannelHandlerContext ctx, String uniqueKey);

    void heartNoReadData(ChannelHandlerContext ctx, String uniqueKey);

    boolean hasOpenHeartCheck();


}
