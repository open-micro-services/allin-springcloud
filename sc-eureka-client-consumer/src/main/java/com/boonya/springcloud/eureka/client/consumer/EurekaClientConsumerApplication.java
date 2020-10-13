package com.boonya.springcloud.eureka.client.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Eureka注册服务消费者sc-eureka-client-consumer
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientConsumerApplication.class, args);
	}

}
