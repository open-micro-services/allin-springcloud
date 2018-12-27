package com.boonya.springcloud.permission.user.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: EsUserController
 * @Description: TODO(功能描述)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-27 16:55
 */
@RestController
@RequestMapping("user")
public class EsUserController {

    Logger logger = LoggerFactory.getLogger(EsUserController.class);

    @Autowired
    private Registration registration; // 服务注册

    @RequestMapping("/info")
    public String info() {
        String serviceId = registration.getServiceId();
        //打印服务的服务id
        logger.info("*********" + serviceId);
        return "user-service:"+serviceId;
    }

   /*
    @Autowired
    DiscoveryClient discoveryClient; // 服务发现客户端--弃用


   @RequestMapping("/discoveryClient")
    public String discoveryClient() {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        //打印服务的服务id
        logger.info("*********" + instance.getServiceId());
        return "user-service";
    }*/


}
