package com.codingapi.sds.socket.service.impl;

import com.codingapi.sds.socket.config.SocketConfig;
import com.codingapi.sds.socket.model.Server;
import com.codingapi.sds.socket.service.ServerService;
import com.codingapi.sds.socket.utils.ByteUtils;
import com.codingapi.sds.socket.utils.SocketManager;
import com.codingapi.sds.socket.utils.SocketUtils;
import com.lorne.core.framework.utils.encode.Base64Utils;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/9/21
 */
@Service
public class ServerServiceImpl implements ServerService {


    @Autowired
    private SocketConfig socketConfig;

    @Override
    public Server getServer() {
        Server server = new Server();
        server.setMaxCount(SocketManager.getInstance().getMaxConnection());
        server.setNowCount(SocketManager.getInstance().getNowConnection());
        server.setIp(socketConfig.getNettyIp());
        server.setPort(socketConfig.getNettyPort());
        server.setTag(socketConfig.getNettyTag());
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
