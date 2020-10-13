package com.boonya.springcloud.eureka.client.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Eureka注册服务提供者sc-eureka-client-provider
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaClientProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientProviderApplication.class, args);
	}

}
