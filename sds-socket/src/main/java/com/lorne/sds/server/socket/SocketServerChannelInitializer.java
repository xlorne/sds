package com.lorne.sds.server.socket;

import com.lorne.sds.server.protocol.DefaultProtocolDecoderService;
import com.lorne.sds.server.protocol.DefaultProtocolEncoderService;
import com.lorne.sds.server.protocol.ProtocolDecoderService;
import com.lorne.sds.server.protocol.ProtocolEncoderService;
import com.lorne.sds.server.service.SocketService;
import com.lorne.sds.server.socket.handler.ProtocolDecoderHandler;
import com.lorne.sds.server.socket.handler.ProtocolEncoderHandler;
import com.lorne.sds.server.socket.handler.SocketHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * create by lorne on 2017/12/26
 */

@Component
public class SocketServerChannelInitializer extends ChannelInitializer<SocketChannel> {



    @Value("${netty.heartTime}")
    private int heartTime = 10;

    @Autowired
    private SocketService socketService;


    @Autowired
    private ApplicationContext applicationContext;


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        logger.info("initChannel-start");

        ProtocolDecoderService protocolDecoderService = null;
        ProtocolEncoderService protocolEncoderService = null;

        try{
            protocolDecoderService = applicationContext.getBean(ProtocolDecoderService.class);
            protocolEncoderService = applicationContext.getBean(ProtocolEncoderService.class);

        }catch (Exception e){
            protocolDecoderService = new DefaultProtocolDecoderService();
            protocolEncoderService = new DefaultProtocolEncoderService();
        }

        logger.info("initChannel->protocolDecoderService:"+protocolDecoderService);
        logger.info("initChannel->protocolEncoderService:"+protocolEncoderService);


        ch.pipeline().addLast(new ByteArrayDecoder());
        ch.pipeline().addLast(new ByteArrayEncoder());

        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 1, 1, 0, 0));

        ch.pipeline().addLast(new ProtocolDecoderHandler(protocolDecoderService));
        ch.pipeline().addLast(new ProtocolEncoderHandler(protocolEncoderService));

        ch.pipeline().addLast(new IdleStateHandler(heartTime, heartTime, heartTime, TimeUnit.SECONDS));
        ch.pipeline().addLast(new SocketHandler(socketService));

        logger.info("initChannel-end");
    }
}
