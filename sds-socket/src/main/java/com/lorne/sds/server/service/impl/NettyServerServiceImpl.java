package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.service.NettyServerService;
import com.lorne.sds.server.service.SocketService;
import com.lorne.sds.server.socket.handler.SocketHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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


    @Value("${netty.port}")
    private int port;


    @Autowired
    private SocketService socketService;


    @Value("${netty.heartTime}")
    private int heartTime = 10;


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

                            ChannelPipeline pipeline = ch.pipeline();
                            //  pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new ByteArrayDecoder());
                            pipeline.addLast("timeout", new IdleStateHandler(heartTime, heartTime, heartTime, TimeUnit.SECONDS));

                            ch.pipeline().addLast(new LengthFieldPrepender(4, false));
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));

                            pipeline.addLast(new SocketHandler(socketService));
                            pipeline.addLast(new ByteArrayEncoder());
                            //pipeline.addLast(new StringEncoder());

                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

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

