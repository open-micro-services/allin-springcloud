package com.boonya.springcloud.eureka.consumer.feign.service.feedback;

import com.boonya.springcloud.eureka.consumer.feign.service.SystemService;
import org.springframework.stereotype.Service;

/**
 * @Copyright: 2019-2021
 * @FileName: SystemFeedBackService.java
 * @Author: PJL
 * @Date: 2020/10/14 15:50
 * @Description: 系统反馈服务
 */
@Service
public class SystemFeedBackService implements SystemService {

    /**
     * 获取系统版本号
     *
     * @return
     */
    @Override
    public String getVersion(){
        return "getVersion===>feedback==feedback";
    }

    /**
     * 获取操作系统信息
     *
     * @return
     */
    @Override
    public String getOsInfo(){
        return "getOsInfo===>feedback==feedback";
    }
}
