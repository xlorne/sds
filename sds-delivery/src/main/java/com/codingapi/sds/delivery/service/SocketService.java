package com.codingapi.sds.delivery.service;

import com.lorne.core.framework.exception.ServiceException;

/**
 * create by lorne on 2017/10/10
 */
public interface SocketService {

    boolean sendHexCmd(String modelName, String uniqueKey, String cmd) throws ServiceException;


    boolean sendBase64Cmd(String modelName, String uniqueKey, String cmd) throws ServiceException;


    boolean sendStrCmd(String modelName, String uniqueKey, String cmd) throws ServiceException;

    boolean sendHexCmdByKey(String key, String cmd) throws ServiceException;

    boolean sendBase64CmdByKey(String key, String cmd) throws ServiceException;

    boolean sendStrCmdByKey(String key, String cmd) throws ServiceException;
}
