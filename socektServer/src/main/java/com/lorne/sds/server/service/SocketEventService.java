package com.lorne.sds.server.service;

import io.netty.channel.ChannelHandlerContext; /**
 * create by lorne on 2017/10/13
 */
public interface SocketEventService {

    /**
     * 读取数据事件
     * @param ctx
     * @param uniqueKey
     * @param msg
     */
    void onReadListener(ChannelHandlerContext ctx, String uniqueKey, Object msg);

    /**
     * 连接服务事件
     * @param ctx
     * @param uniqueKey
     */
    void onConnectionListener(ChannelHandlerContext ctx, String uniqueKey);

    /**
     * 断开连接事件
     * @param ctx
     * @param uniqueKey
     */
    void onDisConnectionListener(ChannelHandlerContext ctx, String uniqueKey);

    /**
     * 心跳写入超时监听
     * @param ctx
     * @param uniqueKey
     */
    void onHeartNoWriteDataListener(ChannelHandlerContext ctx, String uniqueKey);

    /**
     * 心跳读取超时监听
     * @param ctx
     * @param uniqueKey
     */
    void onHeartNoReadDataListener(ChannelHandlerContext ctx, String uniqueKey);

    /**
     * 是否开启心跳检查
     * @return
     */
    boolean hasOpenHeartCheck();


}
