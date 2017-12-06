package com.lorne.sds.server.service.impl;

import com.lorne.core.framework.exception.ServiceException;
import com.lorne.sds.server.model.SocketModel;
import com.lorne.sds.server.service.OnlineService;
import com.lorne.sds.server.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * create by lorne on 2017/12/6
 */
@Service
public class OnLineServiceImpl implements OnlineService{


    @Autowired
    private RedisService redisService;

    @Override
    public boolean add(String modelName, String uniqueKey) {
        redisService.add(modelName, uniqueKey);
        return true;
    }

    @Override
    public boolean remove(String modelName, String uniqueKey) {
        redisService.remove(modelName, uniqueKey);
        return true;
    }


    @Override
    public boolean putKey(String modelName, String uniqueKey, String key) {
        redisService.putKey(modelName, uniqueKey,key);
        return true;
    }

    @Override
    public SocketModel getModelByKey(String key) throws ServiceException {

        String val = redisService.getModelByKey(key);
        if(StringUtils.isEmpty(val)){
            throw new ServiceException("data not exist.");
        }

        String mv[] = val.split("#");
        SocketModel model = new SocketModel();
        model.setModelName(mv[0]);
        model.setUniqueKey(mv[1]);
        return model;
    }
}
