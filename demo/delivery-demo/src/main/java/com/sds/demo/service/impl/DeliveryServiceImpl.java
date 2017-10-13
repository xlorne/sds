package com.sds.demo.service.impl;

import com.lorne.sds.server.service.DeliveryService;
import com.lorne.sds.server.utils.SocketUtils;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/10/13
 */
@Service
public class DeliveryServiceImpl implements DeliveryService {

    public void delivery(ChannelHandlerContext ctx, Object msg) {
        System.out.println("hahaha");

        SocketUtils.send(ctx.channel(),"hahaha".getBytes());
    }
}
