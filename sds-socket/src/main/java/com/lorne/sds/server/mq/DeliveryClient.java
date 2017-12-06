package com.lorne.sds.server.mq;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * create by lorne on 2017/12/6
 */
@FeignClient(value = "delivery")
public interface DeliveryClient {


    @RequestMapping(value = "/online/add",method = RequestMethod.POST)
    boolean add(@RequestParam("modelName") String modelName,@RequestParam("uniqueKey") String uniqueKey);


    @RequestMapping(value = "/online/remove",method = RequestMethod.POST)
    boolean remove(@RequestParam("modelName") String modelName,@RequestParam("uniqueKey") String uniqueKey);


    @RequestMapping(value = "/putKey",method = RequestMethod.POST)
    boolean putKey(@RequestParam("modelName") String modelName,@RequestParam("uniqueKey") String uniqueKey,@RequestParam("key") String key);

}
