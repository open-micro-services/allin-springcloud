package com.boonya.springcloud.eureka.client.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Eureka注册服务提供者sc-eureka-client-provider
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientProviderApplication.class, args);
	}

}
