package com.lorne.sds.server.protocol;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * create by lorne on 2017/12/26
 */
public class DefaultProtocolEncoderService implements ProtocolEncoderService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void encode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) throws Exception {

        logger.debug("DefaultProtocolEncoderService-->encode");

        out.add(Unpooled.buffer().writeBytes(msg));
    }
}
