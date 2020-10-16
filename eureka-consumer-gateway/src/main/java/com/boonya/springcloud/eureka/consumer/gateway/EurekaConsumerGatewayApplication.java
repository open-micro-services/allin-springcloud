package com.boonya.springcloud.eureka.consumer.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * Eureka注册服务网关gateway
 */
@EnableWebFlux
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerGatewayApplication.class, args);
    }

}
