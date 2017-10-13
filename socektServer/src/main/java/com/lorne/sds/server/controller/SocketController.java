package com.lorne.sds.server.controller;

import com.lorne.sds.server.model.Server;
import com.lorne.sds.server.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * create by lorne on 2017/9/21
 */
@RestController
@RequestMapping("/socket")
public class SocketController {


    @Autowired
    private ServerService serverService;

    @ResponseBody
    @RequestMapping(value = "/getServer",method = RequestMethod.GET)
    public Server getServer(){
        return serverService.getServer();
    }

    @ResponseBody
    @RequestMapping(value = "/checkChannel",method = RequestMethod.POST)
    public boolean checkChanel(@RequestParam("uniqueKey") String uniqueKey){
        return serverService.checkChannel(uniqueKey);
    }


    @ResponseBody
    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public boolean sendHexCmd(@RequestParam("uniqueKey")String uniqueKey,
                              @RequestParam("cmd")String cmd){
        return serverService.sendCmd(uniqueKey,cmd);
    }

}
