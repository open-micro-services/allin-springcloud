package com.boonya.springcloud.eureka.consumer.ribbon;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
@RibbonClients
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerRibbonApplication {

    /**
     * 策略参考resources/ribbon-policies
     *
     * 负载均衡的策略对象（默认的轮询策略会失效）
     * @return
     */
    @Bean
    public RandomRule randomRule(){
        return new RandomRule();
    }

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerRibbonApplication.class, args);
    }

}
