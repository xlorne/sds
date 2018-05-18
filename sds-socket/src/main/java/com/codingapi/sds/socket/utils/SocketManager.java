package com.codingapi.sds.socket.utils;


import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lorne on 2017/6/30.
 */
public class SocketManager {

    /**
     * 最大连接数
     */
    private int maxConnection;

    /**
     * 当前连接数
     */
    private int nowConnection;

    /**
     * 允许连接请求 true允许 false拒绝
     */
    private volatile boolean allowConnection = true;

    private Map<String,Channel> clients = null;

    private static SocketManager manager = null;

    public void setMaxConnection(int maxConnection) {
        this.maxConnection = maxConnection;
    }

    public static SocketManager getInstance() {
        if (manager == null){
            synchronized (SocketManager.class){
                if(manager==null){
                    manager = new SocketManager();
                }
            }
        }
        return manager;
    }



    private SocketManager() {
        clients = new ConcurrentHashMap<>();
    }

    public void addClient(Channel client) {
        String modelName = client.remoteAddress().toString();
        clients.put(modelName,client);

        nowConnection = clients.size();

        allowConnection = (nowConnection <= maxConnection );
    }

    public void removeClient(Channel client) {

        String modelName = client.remoteAddress().toString();
        clients.remove(modelName);

        nowConnection = clients.size();

        allowConnection = (nowConnection <= maxConnection);
    }


    public int getMaxConnection() {
        return maxConnection;
    }

    public int getNowConnection() {
        return nowConnection;
    }

    public boolean isAllowConnection() {
        return allowConnection;
    }


    public Channel getChannelByUniqueKey(String uniqueKey) {
        return clients.get(uniqueKey);
    }

}
