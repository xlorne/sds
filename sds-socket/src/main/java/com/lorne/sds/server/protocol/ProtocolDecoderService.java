package com.lorne.sds.server.protocol;

import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * create by lorne on 2017/12/26
 */
public interface ProtocolDecoderService {

    void decode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception;
}
