package com.codingapi.sds.server.service;

import io.netty.channel.ChannelHandlerContext;

/**
 * create by lorne on 2017/10/13
 */
public interface SocketEventService {

    /**
     * 读取数据事件
     * @param ctx   socket连接对象
     * @param uniqueKey 连接唯一标示
     * @param msg   读取的数据
     */
    void onReadListener(ChannelHandlerContext ctx, String uniqueKey, Object msg);

    /**
     * 连接服务事件
     * @param ctx   socket连接对象
     * @param uniqueKey 连接的唯一标示
     */
    void onConnectionListener(ChannelHandlerContext ctx, String uniqueKey);

    /**
     * 断开连接事件
     * @param ctx   socket连接对象
     * @param uniqueKey 连接的唯一标示
     */
    void onDisConnectionListener(ChannelHandlerContext ctx, String uniqueKey);

    /**
     * 心跳写入超时监听
     * @param ctx   socket连接对象
     * @param uniqueKey 连接的唯一标示
     */
    void onHeartNoWriteDataListener(ChannelHandlerContext ctx, String uniqueKey);

    /**
     * 心跳读取超时监听
     * @param ctx   socket连接对象
     * @param uniqueKey 连接的唯一标示
     */
    void onHeartNoReadDataListener(ChannelHandlerContext ctx, String uniqueKey);

    /**
     * 是否开启心跳检查
     * @return  是否开启检查心跳 true 开启，false 关闭
     */
    boolean hasOpenHeartCheck();


}
