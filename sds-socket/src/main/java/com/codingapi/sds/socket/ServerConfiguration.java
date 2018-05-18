package com.codingapi.sds.socket;

import com.codingapi.sds.socket.config.SocketConfig;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableDiscoveryClient
@EnableFeignClients
public class ServerConfiguration {

    @Bean
    public SocketConfig socketConfig(){
        return new SocketConfig();
    }

}
