package com.codingapi.sds.socket.protocol;

import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * create by lorne on 2017/12/26
 */
public class DefaultProtocolDecoderService implements ProtocolDecoderService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    public void decode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception {

        logger.debug("DefaultProtocolDecoderService-->decode");

        out.add(msg);
    }
}
