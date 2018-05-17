package com.codingapi.sds.server;

import com.codingapi.sds.server.config.DeliveryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
@ComponentScan
public class DeliveryConfiguration {


	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public DeliveryConfig deliveryConfig(){
		return new DeliveryConfig();
	}

}
