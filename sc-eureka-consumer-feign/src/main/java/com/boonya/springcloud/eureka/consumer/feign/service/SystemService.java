package com.boonya.springcloud.eureka.consumer.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Copyright: 2019-2021
 * @FileName: SystemService.java
 * @Author: PJL
 * @Date: 2020/10/13 13:37
 * @Description: 系统服务接口
 */
@FeignClient(value = "systemService")
public interface SystemService {

    /**
     * 获取系统版本号
     *
     * @return
     */
    @RequestMapping(value = "/getVersion", method = RequestMethod.GET)
    String getVersion();

    /**
     * 获取操作系统信息
     *
     * @return
     */
    @RequestMapping(value = "/getVersion", method = RequestMethod.GET)
    String getOsInfo();
}
