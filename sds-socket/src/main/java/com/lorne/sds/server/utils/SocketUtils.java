package com.lorne.sds.server.utils;


import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

/**
 * Created by lorne on 2016/3/7.
 */
public class SocketUtils {


    public static void send( String uniqueKey, final byte[] data) {
        Channel ctx =  SocketManager.getInstance().getChannelByUniqueKey(uniqueKey);
        send(ctx,data);
    }

    public static void send( Channel ctx, final byte[] data) {
        if (ctx == null)
            return;
        final ByteBuf byteBufMsg = ctx.alloc().buffer(data.length);
        byteBufMsg.writeBytes(data);
        ctx.writeAndFlush(byteBufMsg);
    }

}
