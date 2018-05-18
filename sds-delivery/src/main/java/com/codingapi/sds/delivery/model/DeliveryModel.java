package com.codingapi.sds.delivery.model;

import java.util.List;

/**
 * create by lorne on 2017/12/6
 */
public class DeliveryModel {

    private String ip;

    private int port;

    private boolean testRedis;

    private List<String> deliverys;

    private List<String> sockets;

    public List<String> getDeliverys() {
        return deliverys;
    }

    public void setDeliverys(List<String> deliverys) {
        this.deliverys = deliverys;
    }

    public List<String> getSockets() {
        return sockets;
    }

    public void setSockets(List<String> sockets) {
        this.sockets = sockets;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isTestRedis() {
        return testRedis;
    }

    public void setTestRedis(boolean testRedis) {
        this.testRedis = testRedis;
    }
}
