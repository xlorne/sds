package com.codingapi.sds.server.controller;

import com.codingapi.sds.server.service.RedisService;
import com.codingapi.sds.server.service.SocketService;
import com.lorne.core.framework.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public Set<String> index(@RequestParam(name = "modelName") String modelName) {
        return redisService.all(modelName);
    }


    @RequestMapping(value = "/sendHexCmd", method = RequestMethod.POST)
    public boolean sendHexCmd(@RequestParam(name = "modelName") String modelName,
                              @RequestParam(name = "uniqueKey") String uniqueKey,
                              @RequestParam(name = "cmd") String cmd) throws ServiceException {
        return socketService.sendHexCmd(modelName, uniqueKey, cmd);
    }


    @RequestMapping(value = "/sendBase64Cmd", method = RequestMethod.POST)
    public boolean sendBase64Cmd(@RequestParam(name = "modelName") String modelName,
                                 @RequestParam(name = "uniqueKey") String uniqueKey,
                                 @RequestParam(name = "cmd") String cmd) throws ServiceException {
        return socketService.sendBase64Cmd(modelName, uniqueKey, cmd);
    }


    @RequestMapping(value = "/sendStrCmd", method = RequestMethod.POST)
    public boolean sendStrCmd(@RequestParam(name = "modelName") String modelName,
                              @RequestParam(name = "uniqueKey") String uniqueKey,
                              @RequestParam(name = "cmd") String cmd) throws ServiceException {
        return socketService.sendStrCmd(modelName, uniqueKey, cmd);
    }


    @RequestMapping(value = "/sendHexCmdByKey", method = RequestMethod.POST)
    public boolean sendHexCmdByKey(@RequestParam(name = "key") String key,
                                   @RequestParam(name = "cmd") String cmd) throws ServiceException {
        return socketService.sendHexCmdByKey(key, cmd);
    }


    @RequestMapping(value = "/sendBase64CmdByKey", method = RequestMethod.POST)
    public boolean sendBase64CmdByKey(@RequestParam(name = "key") String key,
                                      @RequestParam(name = "cmd") String cmd) throws ServiceException {
        return socketService.sendBase64CmdByKey(key, cmd);
    }


    @RequestMapping(value = "/sendStrCmdByKey", method = RequestMethod.POST)
    public boolean sendStrCmdByKey(@RequestParam(name = "key") String key,
                                   @RequestParam(name = "cmd") String cmd) throws ServiceException {
        return socketService.sendStrCmdByKey(key, cmd);
    }
}
