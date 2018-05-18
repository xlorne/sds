package com.codingapi.sds.delivery.utils;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;

/**
 * create by lorne on 2017/12/28
 */
public class TelnetUtils {


    public static boolean telnet(String host,int port){
        TelnetClient telnetClient = new TelnetClient("vt200");
        //socket延迟时间：5000ms
        telnetClient.setDefaultTimeout(5000);
        try {
            telnetClient.connect(host,port);
            telnetClient.disconnect();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
