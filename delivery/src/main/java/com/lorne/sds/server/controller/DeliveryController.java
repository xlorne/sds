package com.lorne.sds.server.controller;

import com.lorne.core.framework.exception.ServiceException;
import com.lorne.sds.server.service.SocketService;
import com.lorne.sds.server.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * create by lorne on 2017/10/10
 */
@RestController
@RequestMapping("/delivery")
public class DeliveryController {


    @Autowired
    private SocketService socketService;

    @Autowired
    private RedisService redisService;



    @RequestMapping("/index")
    public Set<String> index(@RequestParam(name = "modelName") String modelName) throws ServiceException {
        return redisService.all(modelName);
    }


    @RequestMapping("/send")
    public boolean send(@RequestParam(name = "modelName") String modelName,
                        @RequestParam(name = "uniqueKey") String uniqueKey,
                        @RequestParam(name = "cmd") String cmd) throws ServiceException {
        return socketService.send(modelName,uniqueKey,cmd);
    }


}
