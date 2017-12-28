package com.lorne.sds.server.utils;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;

/**
 * create by lorne on 2017/12/28
 */
public class TelnetUtils {


    public static boolean telnet(String host,int port){
        TelnetClient telnetClient = new TelnetClient("vt200");
        telnetClient.setDefaultTimeout(5000); //socket延迟时间：5000ms
        try {
            telnetClient.connect(host,port);
            telnetClient.disconnect();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
