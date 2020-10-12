package com.boonya.springcloud.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName: EurekaClientApplication
 * @Description: TODO(用来指定该项目为Eureka的服务注册客户端)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-26 9:59
 */
@SpringBootApplication
@EnableEurekaClient
public class EurekaClientApplication {

    /**
     * 函数入口
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
}
