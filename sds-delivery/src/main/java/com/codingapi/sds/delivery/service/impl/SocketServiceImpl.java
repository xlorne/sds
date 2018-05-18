package com.codingapi.sds.delivery.service.impl;

import com.codingapi.sds.delivery.service.OnlineService;
import com.codingapi.sds.delivery.service.SocketService;
import com.lorne.core.framework.exception.ServiceException;
import com.codingapi.sds.delivery.model.SocketModel;
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

    @Autowired
    private OnlineService onlineService;


    @Override
    public boolean sendHexCmd(String modelName, String uniqueKey, String cmd) throws ServiceException {
        String url = "http://"+modelName+"/socket/sendHexCmd";
        return restTemplate.postForObject(url+"?uniqueKey={uniqueKey}&cmd={cmd}",null,Boolean.class,uniqueKey,cmd);
    }


    @Override
    public boolean sendBase64Cmd(String modelName, String uniqueKey, String cmd) throws ServiceException {
        String url = "http://"+modelName+"/socket/sendBase64Cmd";
        return restTemplate.postForObject(url+"?uniqueKey={uniqueKey}&cmd={cmd}",null,Boolean.class,uniqueKey,cmd);
    }


    @Override
    public boolean sendStrCmd(String modelName, String uniqueKey, String cmd) throws ServiceException {
        String url = "http://"+modelName+"/socket/sendStrCmd";
        return restTemplate.postForObject(url+"?uniqueKey={uniqueKey}&cmd={cmd}",null,Boolean.class,uniqueKey,cmd);
    }


    @Override
    public boolean sendHexCmdByKey(String key, String cmd) throws ServiceException{
        SocketModel socketModel =  onlineService.getModelByKey(key);
        return sendHexCmd(socketModel.getModelName(),socketModel.getUniqueKey(),cmd);
    }

    @Override
    public boolean sendBase64CmdByKey(String key, String cmd)throws ServiceException {
        SocketModel socketModel =  onlineService.getModelByKey(key);
        return sendBase64Cmd(socketModel.getModelName(),socketModel.getUniqueKey(),cmd);
    }

    @Override
    public boolean sendStrCmdByKey(String key, String cmd)throws ServiceException {
        SocketModel socketModel =  onlineService.getModelByKey(key);
        return sendStrCmd(socketModel.getModelName(),socketModel.getUniqueKey(),cmd);
    }
}
