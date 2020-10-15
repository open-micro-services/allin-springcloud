package com.boonya.springcloud.messages.rabbitmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: RabbitProducer
 * @Description: TODO(RabbitMQ生产者[RabbitTemplate是Spring AMQP MQ消息中间件标准的一个默认实现])
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 11:48
 */
@Slf4j
@Service
public class RabbitProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 通过*Exchange 分发消息
     *
     * @param exchange
     * @param routingKey
     * @param msg
     */
    public void send(String exchange, String routingKey, Message msg) {
        log.info("RabbitTemplate send : " + msg);
        // 发消息，参数类型为org.springframework.amqp.core.Message
        rabbitTemplate.send(exchange, routingKey, msg);
    }

    /**
     * 通过*Exchange 分发消息
     *
     * @param exchange
     * @param routingKey
     * @param msg
     */
    public void convertAndSend(String exchange, String routingKey, String msg) {
        log.info("RabbitTemplate convertAndSend : " + msg);
        // 转换并发送消息。 将参数对象转换为org.springframework.amqp.core.Message后发送
        rabbitTemplate.convertAndSend(exchange, routingKey, msg);
    }

    /**
     * 通过*Exchange 分发消息
     *
     * @param exchange
     * @param routingKey
     * @param msg
     */
    public void convertSendAndReceive(String exchange, String routingKey, String msg) {
        log.info("RabbitTemplate convertSendAndReceive : " + msg);
        // 转换并发送消息,且等待消息者返回响应消息
        rabbitTemplate.convertSendAndReceive(exchange, routingKey, msg);
    }

}
