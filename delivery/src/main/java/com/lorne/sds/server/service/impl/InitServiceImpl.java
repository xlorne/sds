package com.lorne.sds.server.service.impl;


import com.lorne.sds.server.service.DeliveryService;
import com.lorne.sds.server.service.NettyServerService;
import com.lorne.sds.server.service.InitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

/**
 * create by lorne on 2017/8/15
 */
@Service
public class InitServiceImpl implements InitService {

    @Autowired
    private NettyServerService nettyServerService;

    @Autowired
    private DeliveryService deliveryService;


    private Timer timer = new Timer();


    private static final int maxDelayTime = 1000*60*10;

    @Override
    public void start() {
        nettyServerService.start();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    deliveryService.checkSocket();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },maxDelayTime,maxDelayTime);
    }

    @Override
    public void close() {
        nettyServerService.close();
    }
}
