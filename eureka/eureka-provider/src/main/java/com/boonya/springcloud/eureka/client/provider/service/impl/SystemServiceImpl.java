package com.boonya.springcloud.eureka.client.provider.service.impl;

import com.boonya.springcloud.eureka.client.provider.service.SystemService;
import org.springframework.stereotype.Service;

/**
 * @Copyright: 2019-2021
 * @FileName: SystemServiceImpl.java
 * @Author: PJL
 * @Date: 2020/10/13 13:39
 * @Description: 系统服务接口实现
 */
@Service
public class SystemServiceImpl implements SystemService {

    /**
     * 获取系统版本号
     *
     * @return
     */
    @Override
    public String getVersion(){
        return "getVersion";
    }

    /**
     * 获取操作系统信息
     *
     * @return
     */
    @Override
    public String getOsInfo(){
        return "getOsInfo";
    }

}
