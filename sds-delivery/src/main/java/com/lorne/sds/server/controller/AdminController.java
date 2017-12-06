package com.lorne.sds.server.controller;

import com.lorne.sds.server.model.DeliveryModel;
import com.lorne.sds.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * create by lorne on 2017/12/6
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/models")
    public List<String> models(){
        return adminService.models();
    }

    @RequestMapping(value = "/connections",method = RequestMethod.GET)
    public List<String> connections(@RequestParam("key") String key){
        return adminService.connections(key);
    }

    @RequestMapping("/setting")
    public DeliveryModel setting(){
        return adminService.getSetting();
    }


}
