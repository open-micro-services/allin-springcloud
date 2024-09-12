package com.boonya.springcloud.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Copyright: 2019-2021
 * @FileName: RestController.java
 * @Author: PJL
 * @Date: 2020/10/19 15:37
 * @Description: Restful接口(Ribbon负载均衡调用)
 */
@RestController
public class RestfulController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return restTemplate.getForObject("http://nacos-provider/echo/"+string,String.class);
    }
}
