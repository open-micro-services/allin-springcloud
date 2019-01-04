package com.boonya.springcloud.permission.user.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
/**
 * @EnableDiscoveryClient Eureka服务注册和发现
 */
@EnableDiscoveryClient
@MapperScan(basePackages = "com.boonya.springcloud.permission.user.provider.mapper")
@SpringBootApplication
public class UserServiceProviderApplication {

	public static void main(String[] args) {
        SpringApplication.run(UserServiceProviderApplication.class, args);
    }

}

