package com.boonya.springcloud.beans;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @Copyright: 2019-2021
 * @FileName: OSInfo.java
 * @Author: PJL
 * @Date: 2020/4/29 15:18
 * @Description: 操作系统信息
 */
@Component
@Setter
@Getter
public class OSInfo {
    /**
     * 操作系统信息
     */
    private String os;
    /**
     * 上传文件路径
     */
    private String uploadPath;
    /**
     * 下载文件路径
     */
    private String downloadPath;
}
