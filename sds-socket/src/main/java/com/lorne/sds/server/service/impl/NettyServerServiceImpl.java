package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.service.NettyServerService;
import com.lorne.sds.server.service.SocketService;
import com.lorne.sds.server.socket.SocketServerChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by lorne on 2017/4/12.
 */
@Service
public class NettyServerServiceImpl implements NettyServerService {

    @Value("${netty.port}")
    private int port;

    @Value("${netty.heartTime}")
    private int heartTime;

    @Autowired
    private SocketService socketService;

    @Autowired
    private ApplicationContext applicationContext;


    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    private ServerBootstrap b;




    private Logger logger = LoggerFactory.getLogger(NettyServerServiceImpl.class);


    @Override
    public synchronized void start() {
        bossGroup = new NioEventLoopGroup(); // (1)
        workerGroup = new NioEventLoopGroup();
        try {
            b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new SocketServerChannelInitializer(heartTime,socketService,applicationContext));
            // Bind and start to accept incoming connections.
            b.bind(port);

            logger.info("socket: "+port+" starting....");
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

