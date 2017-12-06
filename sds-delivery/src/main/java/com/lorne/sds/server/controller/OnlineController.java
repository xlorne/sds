package com.lorne.sds.server.controller;

import com.lorne.sds.server.service.OnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * create by lorne on 2017/12/6
 */
@RestController
@RequestMapping("/online")
public class OnlineController {

    @Autowired
    private OnlineService onlineService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public boolean add(@RequestParam("modelName") String modelName, @RequestParam("uniqueKey") String uniqueKey){
        return onlineService.add(modelName, uniqueKey);
    }


    @RequestMapping(value = "/remove",method = RequestMethod.POST)
    public boolean remove(@RequestParam("modelName") String modelName,@RequestParam("uniqueKey") String uniqueKey){
        return onlineService.remove(modelName, uniqueKey);
    }


    @RequestMapping(value = "/putKey",method = RequestMethod.POST)
    public boolean putKey(@RequestParam("modelName") String modelName,@RequestParam("uniqueKey") String uniqueKey,@RequestParam("key") String key){
        return onlineService.putKey(modelName, uniqueKey,key);
    }



}
