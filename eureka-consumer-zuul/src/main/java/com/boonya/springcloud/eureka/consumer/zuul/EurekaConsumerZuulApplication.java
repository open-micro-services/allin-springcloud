package com.boonya.springcloud.eureka.consumer.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 * 使用Zuul作为网关进行服务代理（负载均衡）
 *
 * @EnableZuulProxy 提供了一些基本的反向代理过滤器，
 * @EnableZuulServer 只是将zuul指定为一个zuul的server，并不提供任何反向代理的过滤器
 * @RefreshScope yml改变时，不需要手动重启，调用http://localhost:port/refresh,就会加载新的配置文件
 */
@EnableZuulProxy
@EnableHystrix
@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
public class EurekaConsumerZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerZuulApplication.class, args);
    }

}
