package com.lorne.sds.server.socket;


import com.lorne.sds.server.service.DeliveryService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * Created by lorne on 2017/8/12.
 */
public class DeliveryHandler extends ChannelInboundHandlerAdapter {

    private DeliveryService deliveryService;


    public DeliveryHandler(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    private Executor threadPool = Executors.newFixedThreadPool(100);

    @Override
    public void channelRead(final ChannelHandlerContext ctx,final Object msg) { // (2)
        // Discard the received data silently.
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                deliveryService.delivery(ctx,msg);
            }
        });
    }


}
