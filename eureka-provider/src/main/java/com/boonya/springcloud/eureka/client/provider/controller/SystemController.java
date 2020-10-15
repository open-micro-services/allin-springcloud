package com.boonya.springcloud.eureka.client.provider.controller;

import com.boonya.springcloud.eureka.client.provider.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright: 2019-2021
 * @FileName: SystemController.java
 * @Author: PJL
 * @Date: 2020/10/13 13:43
 * @Description: 系统接口控制器
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
    @RequestMapping("/getVersion")
    @ResponseBody
    public String getVersion(){
        return systemService.getVersion();
    }

    /**
     * 获取操作系统信息
     *
     * @return
     */
    @RequestMapping("/getOsInfo")
    @ResponseBody
    public String getOsInfo(){
        return systemService.getOsInfo();
    }
}
