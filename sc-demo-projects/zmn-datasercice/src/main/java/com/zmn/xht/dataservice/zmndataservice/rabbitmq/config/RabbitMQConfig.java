package com.zmn.xht.dataservice.zmndataservice.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RabbitMQConfig
 * @Description: TODO(功能描述)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 12:15
 */
@Configuration
public class RabbitMQConfig {

    //声明队列
    @Bean
    public Queue queueForOrder() {
        return new Queue("order.queue", true); // true表示持久化该队列
    }

    @Bean
    public Queue queueForPay() {
        return new Queue("pay.queue", true); // true表示持久化该队列
    }

    //声明交互器
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    //绑定
    @Bean
    public Binding bindingOrder() {
        return BindingBuilder.bind(queueForOrder()).to(topicExchange()).with("key.order");
    }

    @Bean
    public Binding bindingPay() {
        return BindingBuilder.bind(queueForPay()).to(topicExchange()).with("key.pay");
    }

}
