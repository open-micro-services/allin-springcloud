package com.boonya.springcloud.nacos.provider.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright: 2019-2021
 * @FileName: RestController.java
 * @Author: PJL
 * @Date: 2020/10/19 15:37
 * @Description: Restful接口
 */
@RestController
public class RestfulController {

    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }
}
