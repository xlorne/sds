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

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "success";
    }

    @RequestMapping(value = "/getServer",method = RequestMethod.GET)
    public Server getServer(){
        return serverService.getServer();
    }

    @RequestMapping(value = "/checkChannel",method = RequestMethod.POST)
    public boolean checkChanel(@RequestParam("uniqueKey") String uniqueKey){
        return serverService.checkChannel(uniqueKey);
    }


    @RequestMapping(value = "/sendHexCmd",method = RequestMethod.POST)
    public boolean sendHexCmd(@RequestParam("uniqueKey")String uniqueKey,
                              @RequestParam("cmd")String cmd){
        return serverService.sendHexCmd(uniqueKey,cmd);
    }


    @RequestMapping(value = "/sendBase64Cmd",method = RequestMethod.POST)
    public boolean sendBase64Cmd(@RequestParam("uniqueKey")String uniqueKey,
                              @RequestParam("cmd")String cmd){
        return serverService.sendBase64Cmd(uniqueKey,cmd);
    }

    @RequestMapping(value = "/sendStrCmd",method = RequestMethod.POST)
    public boolean sendStrCmd(@RequestParam("uniqueKey")String uniqueKey,
                                 @RequestParam("cmd")String cmd){
        return serverService.sendStrCmd(uniqueKey,cmd);
    }

}
