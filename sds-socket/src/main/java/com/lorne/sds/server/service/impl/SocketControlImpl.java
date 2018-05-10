package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.mq.DeliveryClient;
import com.lorne.sds.server.service.SocketControl;
import com.lorne.sds.server.socket.SocketServerChannelInitializer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * create by lorne on 2017/10/13
 */
@Service
public class SocketControlImpl implements SocketControl {

    private AttributeKey attributeKey = AttributeKey.valueOf(SocketControlImpl.class.getName());


    @Autowired
    private DeliveryClient deliveryClient;

    @Autowired
    private Registration registration;

    private String ipPort = null;

    @Override
    public String getIpPort() {
        if(ipPort==null) {
            String ipAddress = registration.getHost();
            int port = registration.getPort();
            ipPort = String.format("%s:%d", ipAddress, port);
        }
        return ipPort;
    }

    @Override
    public String getServiceId() {
        return registration.getServiceId();
    }

    @Override
    public String getInstanceId() {
        return registration.getServiceId();
    }

    @Override
    public String getModelName() {
        return getIpPort();
    }


    @Override
    public String getUniqueKey(Channel channel) {
        return channel.remoteAddress().toString();
    }

    @Override
    public String getUniqueKey(ChannelHandlerContext ctx) {
        return getUniqueKey(ctx.channel());
    }


    @Override
    public void bindKey(ChannelHandlerContext ctx, String key) {
        bindKey( ctx.channel(),key);
    }

    @Override
    public void bindKey(Channel ctx, String key) {
        Attribute<String> attr = ctx.attr(attributeKey);
        if(attr!=null){
            attr.set(key);
            //put delivery
            deliveryClient.putKey(getModelName(),getUniqueKey(ctx),key);
        }
    }


    @Override
    public String getKey(Channel channel) {
        if(channel!=null){
            Attribute<String> attr = channel.attr(attributeKey);
            if(attr!=null){
                return attr.get();
            }
        }
        return null;
    }

    @Override
    public String getKey(ChannelHandlerContext ctx) {
        return getKey(ctx.channel());
    }



    @Override
    public void resetHeartTime(Channel ctx,int heartTime){
        ctx.pipeline().remove(SocketServerChannelInitializer.SystemTimeOut);
        ctx.pipeline().addBefore(SocketServerChannelInitializer.SocketHandler,SocketServerChannelInitializer.SystemTimeOut,new IdleStateHandler(heartTime, heartTime, heartTime, TimeUnit.SECONDS));
    }

}
