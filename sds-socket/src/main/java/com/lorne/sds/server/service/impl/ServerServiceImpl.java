package com.lorne.sds.server.service.impl;

import com.lorne.core.framework.utils.encode.Base64Utils;
import com.lorne.sds.server.model.Server;
import com.lorne.sds.server.service.ServerService;
import com.lorne.sds.server.utils.ByteUtils;
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
    public boolean sendHexCmd(String uniqueKey, String cmd) {
        SocketUtils.send(uniqueKey, ByteUtils.fromHexAscii(cmd));
        return true;
    }


    @Override
    public boolean sendBase64Cmd(String uniqueKey, String cmd) {
        SocketUtils.send(uniqueKey, Base64Utils.decode(cmd));
        return true;
    }

    @Override
    public boolean sendStrCmd(String uniqueKey, String cmd) {
        SocketUtils.send(uniqueKey, cmd.getBytes());
        return true;
    }

    @Override
    public boolean checkChannel(String uniqueKey) {
        Channel channel =  SocketManager.getInstance().getChannelByUniqueKey(uniqueKey);
        return channel!=null&&channel.isActive();
    }
}
