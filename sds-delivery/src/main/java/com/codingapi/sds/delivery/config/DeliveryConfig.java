package com.codingapi.sds.delivery.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author lorne
 */
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "codingapi.sds.delivery")
public class DeliveryConfig {

    private int port;

    private int checkTime;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(int checkTime) {
        this.checkTime = checkTime;
    }
}
