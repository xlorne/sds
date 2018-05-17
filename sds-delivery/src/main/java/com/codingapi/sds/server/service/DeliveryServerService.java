package com.codingapi.sds.server.service;

import com.codingapi.sds.server.model.Server;

import java.util.List;

/**
 * create by lorne on 2017/10/14
 */
public interface DeliveryServerService {

    String SOCKET_SERVER_KEY = "SOCKET-SERVER";

    Server getOkServer();

    List<Server> serverList();
}
