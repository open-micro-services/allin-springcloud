package com.boonya.springcloud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: Log
 * @Description: TODO(Logback日志)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2019-01-04 13:15
 */
public class Log {

    /**
     * 获取日志对象
     *
     * @param clazz
     * @return
     */
    public Logger getLogger(Class clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
