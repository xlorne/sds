package com.sds.demo.service.impl;

import com.lorne.sds.server.service.SocketEventService;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/10/13
 */
@Service
public class SocketEventServiceImpl implements SocketEventService {


    @Override
    public void channelRead(ChannelHandlerContext ctx, String uniqueKey, Object msg) {
        System.out.println(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx, String uniqueKey) {
        System.out.println(uniqueKey);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx, String uniqueKey) {

    }

    @Override
    public void heartNoWriteData(ChannelHandlerContext ctx, String uniqueKey) {

    }

    @Override
    public void heartNoReadData(ChannelHandlerContext ctx, String uniqueKey) {

    }

    @Override
    public boolean hasOpenHeartCheck() {
        return false;
    }
}
