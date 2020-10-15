package com.boonya.springcloud.messages.rocketmq.controller;

import com.boonya.springcloud.messages.rocketmq.producer.PayProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

/**
 * 参考文章：https://www.cnblogs.com/wadmwz/p/10689972.html
 */
@RestController
public class PayController {

    @Autowired
    private PayProducer payProducer;

    /**
     * topic,消息依赖于topic
     */
    private static final String topic = "pay_test_topic";


    @RequestMapping("/api/v1/pay_cb")
    public Object callback(String text) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        // 创建消息  主题   二级分类   消息内容好的字节数组
        Message message = new Message(topic, "taga", ("hello rocketMQ " + text).getBytes());

        SendResult send = payProducer.getProducer().send(message);

        System.out.println(send);

        return new HashMap<>();
    }
}
