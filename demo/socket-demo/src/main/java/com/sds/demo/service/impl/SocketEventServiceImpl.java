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
    public void onReadListener(ChannelHandlerContext ctx, String uniqueKey, Object msg) {
        System.out.println(msg);
    }

    @Override
    public void onConnectionListener(ChannelHandlerContext ctx, String uniqueKey) {
        System.out.println(uniqueKey);
    }

    @Override
    public void onDisConnectionListener(ChannelHandlerContext ctx, String uniqueKey) {

    }

    @Override
    public void onHeartNoWriteDataListener(ChannelHandlerContext ctx, String uniqueKey) {

    }

    @Override
    public void onHeartNoReadDataListener(ChannelHandlerContext ctx, String uniqueKey) {

    }

    @Override
    public boolean hasOpenHeartCheck() {
        return true;
    }
}
