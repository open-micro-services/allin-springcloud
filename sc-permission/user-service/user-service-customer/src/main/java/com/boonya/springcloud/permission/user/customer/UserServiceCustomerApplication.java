package com.boonya.springcloud.permission.user.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
/**
 * @EnableDiscoveryClient Eureka服务注册和发现
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UserServiceCustomerApplication {

    //@Bean 应用在方法上，用来将方法返回值设为为bean
    @Bean
    @LoadBalanced  //@LoadBalanced实现Ribbon负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	public static void main(String[] args) {
        SpringApplication.run(UserServiceCustomerApplication.class, args);
    }

}
