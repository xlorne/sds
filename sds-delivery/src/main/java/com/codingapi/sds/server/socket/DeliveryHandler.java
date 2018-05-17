package com.codingapi.sds.server.socket;


import com.codingapi.sds.server.service.DeliveryService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * Created by lorne on 2017/8/12.
 */
public class DeliveryHandler extends ChannelInboundHandlerAdapter {

    private DeliveryService deliveryService;


    public DeliveryHandler(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }


    @Override
    public void channelRead(final ChannelHandlerContext ctx,final Object msg) { // (2)
        // Discard the received data silently.
        deliveryService.delivery(ctx,msg);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);

        deliveryService.channelActive(ctx);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);

        deliveryService.channelInactive(ctx);
    }
}
