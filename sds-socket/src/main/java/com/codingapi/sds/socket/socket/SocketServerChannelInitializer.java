package com.codingapi.sds.socket.socket;

import com.codingapi.sds.socket.protocol.DefaultProtocolDecoderService;
import com.codingapi.sds.socket.protocol.DefaultProtocolEncoderService;
import com.codingapi.sds.socket.protocol.ProtocolDecoderService;
import com.codingapi.sds.socket.protocol.ProtocolEncoderService;
import com.codingapi.sds.socket.service.SocketService;
import com.codingapi.sds.socket.socket.handler.ProtocolDecoderHandler;
import com.codingapi.sds.socket.socket.handler.ProtocolEncoderHandler;
import com.codingapi.sds.socket.socket.handler.SocketHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * create by lorne on 2017/12/26
 */

public class SocketServerChannelInitializer extends ChannelInitializer<SocketChannel> {


    public final static String ByteArrayDecoder = "ByteArrayDecoder";
    public final static String ByteArrayEncoder = "ByteArrayEncoder";
    public final static String LengthFieldBasedFrameDecoder = "LengthFieldBasedFrameDecoder";
    public final static String ProtocolDecoderHandler = "ProtocolDecoderHandler";
    public final static String ProtocolEncoderHandler = "ProtocolEncoderHandler";
    public final static String SystemTimeOut = "SystemTimeOut";
    public final static String SocketHandler = "SocketHandler";

    private static Logger logger = LoggerFactory.getLogger(SocketServerChannelInitializer.class);


    private int heartTime;
    private SocketService socketService;
    private ApplicationContext applicationContext;


    public SocketServerChannelInitializer(int heartTime, SocketService socketService, ApplicationContext applicationContext) {
        this.heartTime = heartTime;
        this.socketService = socketService;
        this.applicationContext = applicationContext;
    }


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        logger.debug("initChannel-start");

        ProtocolDecoderService protocolDecoderService = null;
        ProtocolEncoderService protocolEncoderService = null;

        try{
            protocolDecoderService = applicationContext.getBean(ProtocolDecoderService.class);
            protocolEncoderService = applicationContext.getBean(ProtocolEncoderService.class);

        }catch (Exception e){
            protocolDecoderService = new DefaultProtocolDecoderService();
            protocolEncoderService = new DefaultProtocolEncoderService();
        }

        logger.debug("initChannel->protocolDecoderService:"+protocolDecoderService);
        logger.debug("initChannel->protocolEncoderService:"+protocolEncoderService);


        ch.pipeline().addLast(ByteArrayDecoder,new ByteArrayDecoder());
        ch.pipeline().addLast(ByteArrayEncoder,new ByteArrayEncoder());

        ch.pipeline().addLast(LengthFieldBasedFrameDecoder,new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));

        ch.pipeline().addLast(ProtocolDecoderHandler,new ProtocolDecoderHandler(protocolDecoderService));
        ch.pipeline().addLast(ProtocolEncoderHandler,new ProtocolEncoderHandler(protocolEncoderService));


        ch.pipeline().addLast(SystemTimeOut,new IdleStateHandler(heartTime, heartTime, heartTime, TimeUnit.SECONDS));

        ch.pipeline().addLast(SocketHandler,new SocketHandler(socketService));

        logger.debug("initChannel-end");
    }



}
