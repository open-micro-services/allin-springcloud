package com.boonya.springcloud.messages.rabbitmq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.boonya.springcloud.messages.rabbitmq.bean.Order;
import com.boonya.springcloud.messages.rabbitmq.bean.Pay;
import com.boonya.springcloud.messages.rabbitmq.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Consumer
 * @Description: TODO(RabbitMQ消费者)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 11:50
 */
@Slf4j
@Component
public class Consumer {

    /**
     * 订单消费（消息类型不匹配会报错）
     *
     * @param order
     */
    @RabbitListener(queues = {Constants.QUEUE_ORDER})
    public void consumeOrder(Order order) {
        log.info("consumeOrder order msg:{}", JSONObject.toJSONString(order));
    }

    /**
     * 支付消费（消息类型不匹配会报错）
     *
     * @param pay
     */
    @RabbitListener(queues = {Constants.QUEUE_PAY})
    public void consumePay(Pay pay) {
        log.info("consumePay pay msg:{}", JSONObject.toJSONString(pay));
    }
}
