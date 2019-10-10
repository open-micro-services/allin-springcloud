package com.boonya.springcloud.messages.rocketmq.service;

import org.apache.rocketmq.client.producer.SendResult;

/**
 * 参考文章：https://blog.51cto.com/14230003/2405833
 */
public interface FeePlatMqService {

    public SendResult openAccountMsg(String msgInfo);
}
