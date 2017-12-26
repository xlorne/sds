package com.sds.demo.service.impl;

import com.lorne.sds.server.service.SocketControl;
import com.lorne.sds.server.service.SocketEventService;
import com.lorne.sds.server.utils.SocketUtils;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/10/13
 */
@Service
public class SocketEventServiceImpl implements SocketEventService {

    @Autowired
    private SocketControl socketControl;

    private Logger logger = LoggerFactory.getLogger(SocketEventServiceImpl.class);

    @Override
    public void onReadListener(ChannelHandlerContext ctx, String uniqueKey, Object msg) {

        byte[] datas = (byte[]) msg;

        String modelName = socketControl.getModelName();

        String str = new String(datas);

        logger.info("onReadListener--> modelName->"+modelName+",uniqueKey->"+uniqueKey+",msg->"+str);

        //重置心跳时间规则
        if("heartTime".equals(str)) {
            socketControl.resetHeartTime(ctx.channel(),20);
        }

        ctx.writeAndFlush(datas);
    }

    @Override
    public void onConnectionListener(ChannelHandlerContext ctx, String uniqueKey) {
        String modelName = socketControl.getModelName();

        logger.info("onConnectionListener--> modelName->"+modelName+",uniqueKey->"+uniqueKey);
    }

    @Override
    public void onDisConnectionListener(ChannelHandlerContext ctx, String uniqueKey) {
        String modelName = socketControl.getModelName();

        logger.info("onDisConnectionListener--> modelName->"+modelName+",uniqueKey->"+uniqueKey);
    }

    @Override
    public void onHeartNoWriteDataListener(ChannelHandlerContext ctx, String uniqueKey) {

        logger.info("onHeartNoWriteDataListener");

        //ctx.close();
    }

    @Override
    public void onHeartNoReadDataListener(ChannelHandlerContext ctx, String uniqueKey) {

        logger.info("onHeartNoReadDataListener");

        //ctx.close();
    }

    @Override
    public boolean hasOpenHeartCheck() {
        //开启心跳检测
        return true;
    }
}
