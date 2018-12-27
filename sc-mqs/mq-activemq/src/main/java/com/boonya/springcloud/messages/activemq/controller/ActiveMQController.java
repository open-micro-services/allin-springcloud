package com.boonya.springcloud.messages.activemq.controller;

import com.boonya.springcloud.messages.activemq.producer.Producer;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.jms.Destination;

/**
 * @ClassName: ActiveMQController
 * @Description: TODO(ActiveMQ点对点与发布订阅接口)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-27 19:31
 */
@RestController
@RequestMapping("activemq")
public class ActiveMQController {

    private static final Logger logger = LoggerFactory.getLogger(ActiveMQController.class);

    @Autowired
    private Producer producer;

    @RequestMapping("/queue/{msg}")
    public String sendQueueMsg(@PathVariable("msg") String msg){
        Destination destination=new ActiveMQQueue("serviceQueue");
        for (int i=0;i<6;i++){
            producer.sendMessage(destination,msg);
        }
        return "send message to queue success!";
    }

    @RequestMapping("/topic/{msg}")
    public String sendTopicMsg(@PathVariable("msg") String msg){
        Destination destination=new ActiveMQTopic("serviceTopic");
        for (int i=0;i<6;i++){
            producer.sendMessage(destination,msg);
        }
        return "send topic success!";
    }

}
