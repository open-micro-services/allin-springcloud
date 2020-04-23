package com.boonya.springcloud.messages.rabbitmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: AmqpProducer
 * @Description: TODO(RabbitMQ生产者[AmqpTemplate,AMQP是SpringMQ消息中间件标准])
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 11:48
 */
@Slf4j
@Service
public class AmqpProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 通过*Exchange 分发消息
     *
     * @param exchange
     * @param routingKey
     * @param msg
     */
    public void send(String exchange, String routingKey, Message msg) {
        log.info("AmqpTemplate send : " + msg);
        // 发消息，参数类型为org.springframework.amqp.core.Message
        amqpTemplate.send(exchange, routingKey, msg);
    }

    /**
     * 通过*Exchange 分发消息
     *
     * @param exchange
     * @param routingKey
     * @param msg
     */
    public void convertAndSend(String exchange, String routingKey, String msg) {
        log.info("AmqpTemplate convertAndSend : " + msg);
        // 转换并发送消息。 将参数对象转换为org.springframework.amqp.core.Message后发送
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
    }

    /**
     * 通过*Exchange 分发消息
     *
     * @param exchange
     * @param routingKey
     * @param msg
     */
    public void convertSendAndReceive(String exchange, String routingKey, String msg) {
        log.info("AmqpTemplate convertSendAndReceive : " + msg);
        // 转换并发送消息,且等待消息者返回响应消息
        amqpTemplate.convertSendAndReceive(exchange, routingKey, msg);
    }

}
