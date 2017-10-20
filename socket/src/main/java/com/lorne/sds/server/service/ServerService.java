package com.lorne.sds.server.service;

import com.lorne.sds.server.model.Server;

/**
 * create by lorne on 2017/9/21
 */
public interface ServerService {

    Server getServer();

    boolean sendCmd(String uniqueKey, String cmd);

    boolean checkChannel(String uniqueKey);

}
