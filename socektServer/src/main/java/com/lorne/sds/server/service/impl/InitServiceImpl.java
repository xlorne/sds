package com.lorne.sds.server.service.impl;

import com.lorne.sds.server.service.NettyServerService;
import com.lorne.sds.server.service.InitService;
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
