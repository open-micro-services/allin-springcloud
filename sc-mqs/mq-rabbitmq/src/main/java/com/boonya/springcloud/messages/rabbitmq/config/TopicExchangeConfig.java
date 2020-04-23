package com.boonya.springcloud.messages.rabbitmq.config;

import com.boonya.springcloud.messages.rabbitmq.util.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Copyright: 2019-2021
 * @FileName: TopicExchangeConfig.java
 * @Author: PJL
 * @Date: 2020/4/21 18:52
 * @Description: TopicExchange交换器--主题模式（发布/订阅模式）
 */
@Configuration
public class TopicExchangeConfig {

    @Autowired
    AccessQueue accessQueue;

    /**
     * 声明交换器--交换器是分发消息的组件
     *
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(Constants.TOPIC_EXCHANGE);
    }

    /**
     * 订单-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingOrderForTopicExchange() {
        return BindingBuilder.bind(accessQueue.queueForOrder()).to(topicExchange()).with(Constants.ROUTING_KEY_ORDER_TOPIC);
    }

    /**
     * 支付-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingPayForTopicExchange() {
        return BindingBuilder.bind(accessQueue.queueForPay()).to(topicExchange()).with(Constants.ROUTING_KEY_PAY_TOPIC);
    }

    /**
     * 邮件-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingEmailForTopicExchange() {
        return BindingBuilder.bind(accessQueue.queueForEmail()).to(topicExchange()).with(Constants.ROUTING_KEY_EMAIL_TOPIC);
    }

    /**
     * 短信-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingSmsForTopicExchange() {
        return BindingBuilder.bind(accessQueue.queueForSms()).to(topicExchange()).with(Constants.ROUTING_KEY_SMS_TOPIC);
    }
}
