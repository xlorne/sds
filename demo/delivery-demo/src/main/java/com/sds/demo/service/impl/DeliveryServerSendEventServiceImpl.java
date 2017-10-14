package com.sds.demo.service.impl;

import com.lorne.sds.server.model.Server;
import com.lorne.sds.server.service.DeliveryServerSendEventService;
import com.lorne.sds.server.utils.SocketUtils;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/10/13
 */
@Service
public class DeliveryServerSendEventServiceImpl implements DeliveryServerSendEventService {


    @Override
    public void onDeliveryListener(ChannelHandlerContext ctx, Server server) {
        System.out.println("hahaha");

        SocketUtils.send(ctx.channel(),"hahaha".getBytes());
    }
}
