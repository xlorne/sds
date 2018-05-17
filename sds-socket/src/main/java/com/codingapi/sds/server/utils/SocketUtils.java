package com.codingapi.sds.server.utils;


import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

/**
 * Created by lorne on 2016/3/7.
 */
public class SocketUtils {

    public interface IBack{
        void doing(Channel ctx, byte[] data);
    }

    private static IBack back;

    public static void setBack(IBack newBack){
        back = newBack;
    }

    public static void send( String uniqueKey, final byte[] data) {
        Channel ctx =  SocketManager.getInstance().getChannelByUniqueKey(uniqueKey);
        send(ctx,data);
    }

    public static void send( Channel ctx, final byte[] data) {
        if (ctx == null)
            return;
        if(back!=null){
            back.doing(ctx,data);
        }
        final ByteBuf byteBufMsg = ctx.alloc().buffer(data.length);
        byteBufMsg.writeBytes(data);
        ctx.writeAndFlush(byteBufMsg);
    }

}
