package com.codingapi.sds.socket.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author lorne
 */
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "codingapi.sds.socket")
public class SocketConfig {


    private int port;

    private int heartTime;

    private int maxConnection;

    private int nettyPort;

    private String nettyIp;

    private String nettyTag;

    public int getNettyPort() {
        return nettyPort;
    }

    public void setNettyPort(int nettyPort) {
        this.nettyPort = nettyPort;
    }

    public String getNettyIp() {
        return nettyIp;
    }

    public void setNettyIp(String nettyIp) {
        this.nettyIp = nettyIp;
    }

    public String getNettyTag() {
        return nettyTag;
    }

    public void setNettyTag(String nettyTag) {
        this.nettyTag = nettyTag;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getHeartTime() {
        return heartTime;
    }

    public void setHeartTime(int heartTime) {
        this.heartTime = heartTime;
    }

    public int getMaxConnection() {
        return maxConnection;
    }

    public void setMaxConnection(int maxConnection) {
        this.maxConnection = maxConnection;
    }
}
