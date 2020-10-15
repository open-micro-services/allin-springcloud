package com.zmn.xht.dataservice.zmndataservice.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/**
 * @ClassName: Consumer
 * @Description: TODO(RabbitMQ消费者)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 11:50
 */
@Component
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = {"order.queue"})
    public void receiveOrderMessage(String msg) {
        System.out.println("received order msg: " + msg);
        logger.info("order msg:{}", msg);
    }

    @RabbitListener(queues = {"pay.queue"})
    public void receivePayMessage(String msg) {
        System.out.println("received pay msg: " + msg);
        logger.info("pay msg:{}", msg);
    }
}
