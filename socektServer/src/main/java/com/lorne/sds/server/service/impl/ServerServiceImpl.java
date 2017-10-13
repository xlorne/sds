package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.model.Server;
import com.lorne.sds.server.service.ServerService;
import com.lorne.sds.server.utils.SocketManager;
import com.lorne.sds.server.utils.SocketUtils;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/9/21
 */
@Service
public class ServerServiceImpl implements ServerService {


    @Value(value = "${netty.port}")
    private int port;

    @Value(value = "${netty.ip}")
    private String ip;


    @Override
    public Server getServer() {

        Server server = new Server();
        server.setMaxCount(SocketManager.getInstance().getMaxConnection());
        server.setNowCount(SocketManager.getInstance().getNowConnection());

        server.setIp(ip);
        server.setPort(port);
        return server;
    }


    @Override
    public boolean sendCmd(String uniqueKey, String cmd) {
        SocketUtils.send(uniqueKey,cmd.getBytes());
        return true;
    }

    @Override
    public boolean checkChannel(String uniqueKey) {
        Channel channel =  SocketManager.getInstance().getChannelByUniqueKey(uniqueKey);
        return channel!=null&&channel.isActive();
    }
}
