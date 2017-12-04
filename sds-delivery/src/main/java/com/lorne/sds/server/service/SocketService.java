package com.lorne.sds.server.service;

import com.lorne.core.framework.exception.ServiceException;

/**
 * create by lorne on 2017/10/10
 */
public interface SocketService {

    boolean send(String modelName, String uniqueKey, String cmd) throws ServiceException;
}
