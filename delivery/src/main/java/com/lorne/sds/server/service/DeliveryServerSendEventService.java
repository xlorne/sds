package com.lorne.sds.server.service;

import com.lorne.sds.server.model.Server;
import io.netty.channel.ChannelHandlerContext; /**
 * create by lorne on 2017/10/13
 */
public interface DeliveryServerSendEventService {

    /**
     * 负载均衡分发分配
     * @param ctx   连接对象
     * @param server    提供的socketServer服务信息
     */
    void onDeliveryListener(ChannelHandlerContext ctx, Server server);
}
