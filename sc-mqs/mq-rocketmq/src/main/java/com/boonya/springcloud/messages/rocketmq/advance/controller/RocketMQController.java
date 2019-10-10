package com.boonya.springcloud.messages.rocketmq.advance.controller;

import com.alibaba.fastjson.JSON;
import com.boonya.springcloud.messages.rocketmq.advance.bean.Panda;
import com.boonya.springcloud.messages.rocketmq.advance.bean.User;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: RocketMQController
 * @Description: TODO(消息发布控制器)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2019/10/10 0:23
 * @see  https://blog.csdn.net/soda_lw/article/details/86543636
 */
@RestController
public class RocketMQController {

    /**
     * producer和consumer的Group需要一致
     */
    @Value("${rocketmq.consumer.groupName}")
    private String groupName;
    @Value("${rocketmq.consumer.topic}")
    private String topic;
    @Value("${rocketmq.consumer.tag}")
    private String tag;
    @Value("${rocketmq.consumer.animal.topic}")
    private String animalTopic;
    @Value("${rocketmq.consumer.animal.tag}")
    private String animalTag;

    @RequestMapping("rocketmq/producer/common/topic")
    public String sendTopic() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer=new DefaultMQProducer(groupName);
        User user  = new User("茜茜",18,"女");
        Message message = new Message(topic, tag, JSON.toJSONString(user).getBytes());
        SendResult result = producer.send(message);
        System.out.println("发送了User消息" + result);

        producer.shutdown();
        System.out.println("producer shutdown!");
        return "success";
    }

    @RequestMapping("rocketmq/producer/animal/topic")
    public String send() throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        DefaultMQProducer producer=new DefaultMQProducer(groupName);
        Panda panda = new Panda("汗血宝马", 5);
        Message pandaMessage = new Message(animalTopic, animalTag, JSON.toJSONString(panda).getBytes());
        SendResult animalResult =  producer.send(pandaMessage);
        System.out.println("发送了Animal消息" +animalResult );

        producer.shutdown();
        System.out.println("producer shutdown!");
        return "success";
    }
}
