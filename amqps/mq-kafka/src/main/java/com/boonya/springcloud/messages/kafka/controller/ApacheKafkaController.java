package com.boonya.springcloud.messages.kafka.controller;

import com.boonya.springcloud.messages.kafka.entity.Message;
import com.boonya.springcloud.messages.kafka.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: ApacheKafkaController
 * @Description: TODO(Kafka消息发布订阅接口)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 1:58
 */
@RestController
@RequestMapping("kafka")
public class ApacheKafkaController {

    @Autowired
    Producer producer;

    @RequestMapping("/send/{msg}")
    public String sendMsg(@PathVariable("msg") String msg) {
        Message message = new Message(UUID.randomUUID().toString(), msg, new Date());
        producer.sendMessage(message);
        return "send message success!";
    }
}
