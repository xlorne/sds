package com.sds.demo.service.impl;

import com.lorne.sds.server.model.Server;
import com.lorne.sds.server.service.DeliveryServerSendEventService;
import com.lorne.sds.server.service.DeliveryServerService;
import com.lorne.sds.server.utils.SocketUtils;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/10/13
 */
@Service
public class DeliveryServerSendEventServiceImpl implements DeliveryServerSendEventService {



    @Autowired
    private DeliveryServerService deliveryServerService;


    @Override
    public void onDeliveryListener(ChannelHandlerContext ctx, Object msg) {
        //获取服务
        Server server =  deliveryServerService.getOkServer();
        //发送信息
        SocketUtils.send(ctx.channel(),server.toString().getBytes());
    }
}
