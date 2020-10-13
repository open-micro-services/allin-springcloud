package com.boonya.springcloud.eureka.consumer.feign.controller;

import com.boonya.springcloud.eureka.consumer.feign.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    SystemService systemService;

    /**
     * 获取系统版本号
     *
     * @return
     */
    @RequestMapping(value = "/getVersion",method = RequestMethod.GET)
    @ResponseBody
    public String getVersion(){
        return systemService.getVersion();
    }

    /**
     * 获取操作系统信息
     *
     * @return
     */
    @RequestMapping(value = "/getOsInfo",method = RequestMethod.GET)
    @ResponseBody
    public String getOsInfo(){
        return systemService.getOsInfo();
    }
}
