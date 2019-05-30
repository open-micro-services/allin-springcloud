package com.boonya.springcloud.permission.user.provider.controller;

import com.boonya.springcloud.beans.permission.entity.EsUser;
import com.boonya.springcloud.beans.permission.service.EsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: EsUserController
 * @Description: TODO(用户微服务提供者管理接口控制器)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-27 16:55
 */
@RestController
@RequestMapping("user")
public class EsUserController {

    Logger logger = LoggerFactory.getLogger(EsUserController.class);

    @Autowired
    EsUserService esUserService;

    @Autowired
    private Registration registration; // 服务注册

    @RequestMapping("/info")
    public String info() {
        String serviceId = registration.getServiceId();
        //打印服务的服务id
        logger.info("*********" + serviceId);
        return "user-service:" + serviceId;
    }

    @RequestMapping("/{id}")
    public EsUser findById(@PathVariable("id") Integer id) {
        return esUserService.findById(id);
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
