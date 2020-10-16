package com.boonya.springcloud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: LoggerUtils
 * @Description: TODO(Logback日志)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2019-01-04 13:15
 */
public class LoggerUtils {

    /**
     * 获取日志对象
     *
     * @param clazz
     * @return
     */
    public Logger getLogger(Class clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * Logback打印到指定的文件下
     *
     * @param loggerType 日志文件类型
     * @return
     */
    public static Logger getLogger(LoggerType loggerType) {
        return LoggerFactory.getLogger(loggerType.getLogFileName());
    }

    /**
     * @Copyright: 2019-2021
     * @FileName: PatrolLoggerType.java
     * @Author: PJL
     * @Date: 2020/9/8 10:10
     * @Description: 日志类型枚举
     */
    public enum LoggerType {
        /**
         * REDIS日志
         */
        REDIS("REDIS_LOG"),
        /**
         * 重启日志
         */
        RESTART("RESTART_LOG"),
        /**
         * 位置日志
         */
        POSITION("POSITION_LOG"),
        /**
         * 事件日志
         */
        EVENT("EVENT_LOG"),
        /**
         * 文件日志
         */
        FILE("FILE_LOG"),
        /**
         * 错误日志
         */
        ERROR("ERROR_LOG"),
        /**
         * 警告日志
         */
        WARN("WARN_LOG"),
        /**
         * 信息日志
         */
        INFO("INFO_LOG"),
        /**
         * 跟踪日志
         */
        TRACK("TRACK_LOG"),
        /**
         * 调试日志
         */
        DEBUG("DEBUG_LOG");

        private String logFileName;

        LoggerType(String fileName) {
            this.logFileName = fileName;
        }

        public String getLogFileName() {
            return logFileName;
        }


    }
}
