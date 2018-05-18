package com.codingapi.sds.socket.listener;

import com.codingapi.sds.socket.config.SocketConfig;
import com.codingapi.sds.socket.service.InitService;
import com.codingapi.sds.socket.utils.SocketManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by lorne on 2017/7/1.
 */

@Component
public class ServerListener implements ServletContextListener {

    private WebApplicationContext springContext;

    private InitService initService;

    @Autowired
    private SocketConfig socketConfig;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        springContext = WebApplicationContextUtils
                .getWebApplicationContext(event.getServletContext());
        initService = springContext.getBean(InitService.class);
        initService.start();

        SocketManager.getInstance().setMaxConnection(socketConfig.getMaxConnection());

    }


    @Override
    public void contextDestroyed(ServletContextEvent event) {
        initService.close();
    }

}
