package com.lorne.sds.server.listener;

import com.lorne.sds.server.Constant;

import com.lorne.sds.server.service.InitService;
import com.lorne.sds.server.utils.SocketManager;
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

    @Value(value = "${netty.max.connection}")
    private int maxConnection;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        springContext = WebApplicationContextUtils
                .getWebApplicationContext(event.getServletContext());
        initService = springContext.getBean(InitService.class);
        initService.start();

        Constant.springContext = springContext;

        SocketManager.getInstance().setMaxConnection(maxConnection);

    }


    @Override
    public void contextDestroyed(ServletContextEvent event) {
        initService.close();
    }

}
