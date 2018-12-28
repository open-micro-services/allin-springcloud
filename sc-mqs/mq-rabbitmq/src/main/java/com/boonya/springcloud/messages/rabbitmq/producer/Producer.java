package com.boonya.springcloud.messages.rabbitmq.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Producer
 * @Description: TODO(RabbitMQ生产者)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 11:48
 */
@Component
public class Producer {

    private  final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendOrderMessage(String msg) {
//        //发消息，参数类型为org.springframework.amqp.core.Message
//        rabbitTemplate.send(message);
//        //转换并发送消息。 将参数对象转换为org.springframework.amqp.core.Message后发送
//        rabbitTemplate.convertAndSend(object);
//        //转换并发送消息,且等待消息者返回响应消息
//        rabbitTemplate.convertSendAndReceive(message);

        System.out.println("开始发送消息 : " + msg);
        rabbitTemplate.convertSendAndReceive("topicExchange", "key.order", msg);
        System.out.println("结束发送消息 : " + msg);
        System.out.println("消费者响应 : 消息处理完成");
        logger.info("sendPayMessage exception:{}",msg);
    }

    public void sendPayMessage(String msg) {
//        //发消息，参数类型为org.springframework.amqp.core.Message
//        rabbitTemplate.send(message);
//        //转换并发送消息。 将参数对象转换为org.springframework.amqp.core.Message后发送
//        rabbitTemplate.convertAndSend(object);
//        //转换并发送消息,且等待消息者返回响应消息
//        rabbitTemplate.convertSendAndReceive(message);

        System.out.println("开始发送消息 : " + msg);
        rabbitTemplate.convertSendAndReceive("topicExchange", "key.pay", msg);
        System.out.println("结束发送消息 : " + msg);
        System.out.println("消费者响应 :消息处理完成");
        logger.info("sendPayMessage exception:{}",msg);
    }

}
