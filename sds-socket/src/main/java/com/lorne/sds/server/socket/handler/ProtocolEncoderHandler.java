package com.lorne.sds.server.socket.handler;

import com.lorne.sds.server.protocol.ProtocolEncoderService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * create by lorne on 2017/12/26
 */

@ChannelHandler.Sharable
public class ProtocolEncoderHandler extends MessageToMessageEncoder<byte[]>{


    private ProtocolEncoderService protocolEncoderService;

    public ProtocolEncoderHandler(ProtocolEncoderService protocolEncoderService) {
        this.protocolEncoderService = protocolEncoderService;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception {
        protocolEncoderService.encode(ctx, msg, out);
    }
}
