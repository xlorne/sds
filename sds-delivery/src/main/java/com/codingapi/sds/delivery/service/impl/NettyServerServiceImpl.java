package com.codingapi.sds.delivery.service.impl;


import com.codingapi.sds.delivery.service.DeliveryService;
import com.codingapi.sds.delivery.service.NettyServerService;
import com.codingapi.sds.delivery.service.SettingService;
import com.codingapi.sds.delivery.socket.DeliveryHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by lorne on 2017/4/12.
 */
@Service
public class NettyServerServiceImpl implements NettyServerService {


    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap b;


    @Autowired
    private SettingService settingService;

    private int heartTime = 5;



    @Autowired
    private DeliveryService deliveryService;


    private Logger logger = LoggerFactory.getLogger(NettyServerServiceImpl.class);


    @Override
    public synchronized void start() {
        bossGroup = new NioEventLoopGroup(); // (1)
        workerGroup = new NioEventLoopGroup();
        try {
            b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {

                            ch.pipeline().addLast(new ByteArrayDecoder());
                            ch.pipeline().addLast(new ByteArrayEncoder());

                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));

                            ch.pipeline().addLast(new IdleStateHandler(heartTime, heartTime, heartTime, TimeUnit.SECONDS));

                            ch.pipeline().addLast(new DeliveryHandler(deliveryService));

                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // Bind and start to accept incoming connections.
            b.bind(settingService.getDeliveryPort());

            logger.info("socket: "+settingService.getDeliveryPort()+" starting....");
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void close() {
        if (workerGroup != null)
            workerGroup.shutdownGracefully();
        if (bossGroup != null)
            bossGroup.shutdownGracefully();
        logger.info("socket closing....");
    }

}

