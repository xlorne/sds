package com.codingapi.sds.delivery.service.impl;


import com.codingapi.sds.delivery.service.DeliveryService;
import com.codingapi.sds.delivery.service.InitService;
import com.codingapi.sds.delivery.service.NettyServerService;
import com.codingapi.sds.delivery.service.SettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    @Autowired
    private SettingService settingService;


    private Logger logger = LoggerFactory.getLogger(InitServiceImpl.class);


    private Timer timer = new Timer();

    //默认 10分钟
    private static final int defaultDelayTime = 10;

    @Override
    public void start() {
        nettyServerService.start();

        int checkTime = settingService.getCheckTime();
        if(checkTime<=0){
            checkTime = defaultDelayTime;
        }

        logger.info("init ->start,check time(min):"+checkTime);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    deliveryService.checkSocket();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },1000*10,checkTime*(1000*60));
    }

    @Override
    public void close() {

        timer.cancel();

        nettyServerService.close();
    }
}
