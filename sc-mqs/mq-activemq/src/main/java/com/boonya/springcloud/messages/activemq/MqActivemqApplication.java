package com.boonya.springcloud.messages.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * JMS 实现ActiveMQ 消息订阅/发布 & 点对点
 */
@SpringBootApplication
public class MqActivemqApplication {

    public static void main(String[] args) {
		SpringApplication.run(MqActivemqApplication.class, args);
	}

}

