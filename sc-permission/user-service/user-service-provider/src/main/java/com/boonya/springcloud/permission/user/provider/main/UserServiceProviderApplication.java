package com.boonya.springcloud.permission.user.provider.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @EnableDiscoveryClient Eureka服务注册和发现
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.boonya.springcloud.permission.user.provider.controller")
public class UserServiceProviderApplication {

	public static void main(String[] args) {
        SpringApplication.run(UserServiceProviderApplication.class, args);
    }

}

