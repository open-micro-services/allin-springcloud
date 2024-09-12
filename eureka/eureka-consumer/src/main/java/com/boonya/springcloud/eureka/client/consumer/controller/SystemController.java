package com.boonya.springcloud.eureka.client.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Copyright: 2019-2021
 * @FileName: SystemController.java
 * @Author: PJL
 * @Date: 2020/10/13 13:56
 * @Description: 系统服务接口控制器
 */
@RestController
public class SystemController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * @LoadBalanced 使用Ribbon实现负载均衡。Ribbon是一个基于HTTP和TCP客户端的负载均衡器。Feign中也使用Ribbon
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
       return new RestTemplate();
    }

    /**
     * 获取系统版本号
     *
     * @return
     */
    @RequestMapping("/getVersion")
    @ResponseBody
    public String getVersion(){
        return restTemplate.getForObject("http://eureka-provider/getVersion",String.class);
    }

    /**
     * 获取操作系统信息
     *
     * @return
     */
    @RequestMapping("/getOsInfo")
    @ResponseBody
    public String getOsInfo(){
        return restTemplate.getForObject("http://eureka-provider/getOsInfo",String.class);
    }
}
