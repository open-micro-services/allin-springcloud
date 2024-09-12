package com.boonya.springcloud.nacos.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Copyright: 2019-2021
 * @FileName: RibbonConfig.java
 * @Author: PJL
 * @Date: 2020/10/19 15:45
 * @Description: Ribbon负载均衡配置
 */
@Configuration
public class RibbonConfig {

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
