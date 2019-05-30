package com.boonya.springcloud.permission.user.customer.controller;

import com.boonya.springcloud.beans.permission.entity.EsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: EsUserController
 * @Description: TODO(用户微服务消费者管理接口控制器)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-26 12:12
 */
@RestController
@RequestMapping("user")
public class EsUserController {

    Logger logger = LoggerFactory.getLogger(EsUserController.class);

    //这里注入的restTemplate就是在UserServiceCustomerApplication中通过@Bean配置的实例
    @Autowired
    RestTemplate restTemplate;

    /**
     * 微服务消费者调用微服务提供者
     *
     * @return
     */
    @RequestMapping("/info")
    public String info() {
        return restTemplate.getForObject("http://localhost:9090/user/info", String.class);
    }

    /**
     * 微服务消费者调用微服务提供者
     *
     * @return
     */
    @RequestMapping("/{id}")
    public EsUser findById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject("http://localhost:9090/user/" + id, EsUser.class);
    }


}
