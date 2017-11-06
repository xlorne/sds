package com.sds.demo.service.impl;

import com.lorne.sds.server.service.EurekaRegistrationService;
import com.lorne.sds.server.service.SocketEventService;
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
    private EurekaRegistrationService eurekaRegistrationService;

    private Logger logger = LoggerFactory.getLogger(SocketEventServiceImpl.class);

    @Override
    public void onReadListener(ChannelHandlerContext ctx, String uniqueKey, Object msg) {

        String modelName = eurekaRegistrationService.getModelName();

        logger.info("onReadListener--> modelName->"+modelName+",uniqueKey->"+uniqueKey+",msg->"+msg);

    }

    @Override
    public void onConnectionListener(ChannelHandlerContext ctx, String uniqueKey) {
        String modelName = eurekaRegistrationService.getModelName();

        logger.info("onConnectionListener--> modelName->"+modelName+",uniqueKey->"+uniqueKey);
    }

    @Override
    public void onDisConnectionListener(ChannelHandlerContext ctx, String uniqueKey) {
        String modelName = eurekaRegistrationService.getModelName();

        logger.info("onDisConnectionListener--> modelName->"+modelName+",uniqueKey->"+uniqueKey);
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
