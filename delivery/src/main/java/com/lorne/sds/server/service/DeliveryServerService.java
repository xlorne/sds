package com.lorne.sds.server.service;

import com.lorne.sds.server.model.Server;

/**
 * create by lorne on 2017/10/14
 */
public interface DeliveryServerService {

    String SOCKET_SERVER_KEY = "SOCKET-SERVER";

    Server getOkServer();
}
