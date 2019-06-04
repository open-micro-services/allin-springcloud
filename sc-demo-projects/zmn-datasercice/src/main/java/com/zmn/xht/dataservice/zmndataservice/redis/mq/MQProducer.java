package com.zmn.xht.dataservice.zmndataservice.redis.mq;


import com.zmn.xht.dataservice.zmndataservice.redis.RedisService;

/**
 * 队列消息管理和生产类
 */
public class MQProducer {

    static{
        // 启动消费线程
        new MQConsumer().start();
    }

    /**
     * 队列发送消息
     * @param message
     */
    public  static void produce(String message) {
        //从左边向堆栈顺序存放消息
        RedisService.getRedisMQ().leftPush("", message);
    }

    /**
     * 队列发送消息
     * @param message
     */
    public  static void produce(String channel,String message) {
        //从左边向堆栈顺序存放消息
        RedisService.getRedisMQ().leftPush(channel, message);
    }

}