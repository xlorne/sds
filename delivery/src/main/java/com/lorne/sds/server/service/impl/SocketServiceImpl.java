package com.lorne.sds.server.service.impl;

import com.lorne.core.framework.exception.ServiceException;
import com.lorne.sds.server.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * create by lorne on 2017/10/10
 */
@Service
public class SocketServiceImpl implements SocketService {


    @Autowired
    private RestTemplate restTemplate;


    @Override
    public boolean send(String modelName, String uniqueKey, String cmd) throws ServiceException {
        String url = "http://"+modelName+"/socket/send";
        return restTemplate.postForObject(url+"?uniqueKey={uniqueKey}&cmd={cmd}",null,Boolean.class,uniqueKey,cmd);
    }
}
