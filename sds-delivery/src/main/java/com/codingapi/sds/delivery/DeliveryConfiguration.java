package com.codingapi.sds.delivery;

import com.codingapi.sds.delivery.config.DeliveryConfig;
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
