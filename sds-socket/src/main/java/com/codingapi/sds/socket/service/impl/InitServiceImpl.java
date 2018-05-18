package com.codingapi.sds.socket.service.impl;

import com.codingapi.sds.socket.service.InitService;
import com.codingapi.sds.socket.service.NettyServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/9/20
 */
@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private NettyServerService nettyServerService;

    @Override
    public void start() {
        nettyServerService.start();
    }

    @Override
    public void close() {
        nettyServerService.close();
    }
}
