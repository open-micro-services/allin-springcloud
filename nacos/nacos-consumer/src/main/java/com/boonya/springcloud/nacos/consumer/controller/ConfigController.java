package com.boonya.springcloud.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Copyright: 2019-2021
 * @FileName: ConfigController.java
 * @Author: PJL
 * @Date: 2020/10/19 15:21
 * @Description: 配置更新服务访问(Ribbon负载均衡调用)
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/get")
    public Boolean get() {
        return restTemplate.getForObject("http://nacos-provider/config/get",Boolean.class);
    }
}
