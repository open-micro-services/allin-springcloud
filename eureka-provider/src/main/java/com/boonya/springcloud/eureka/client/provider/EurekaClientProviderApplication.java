package com.boonya.springcloud.eureka.client.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * Eureka注册服务提供者sc-eureka-client-provider
 */
@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
public class EurekaClientProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientProviderApplication.class, args);
	}

}
