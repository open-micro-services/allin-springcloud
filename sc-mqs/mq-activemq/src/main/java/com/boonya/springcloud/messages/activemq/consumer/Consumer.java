package com.boonya.springcloud.messages.activemq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Consumer
 * @Description: TODO(功能描述)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-27 19:33
 */
@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @JmsListener(destination = "serviceQueue")
    public void receiveMessage(String text) {
        System.out.println("Listener....receive serviceQueue msg....." + text);
    }

    @JmsListener(destination = "serviceTopic")
    public void receiveTopicMessage(String text) {
        System.out.println("Listener....receive serviceTopic msg....." + text);
    }

}
