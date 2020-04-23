package com.boonya.springcloud.messages.rabbitmq.config;

import com.boonya.springcloud.messages.rabbitmq.util.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Copyright: 2019-2021
 * @FileName: DirectExchangeConfig.java
 * @Author: PJL
 * @Date: 2020/4/21 18:57
 * @Description: DirectExchange交换器--定向模式（把消息交给符合指定 rotingKey 的队列）
 */
@Configuration
public class DirectExchangeConfig {

    @Autowired
    AccessQueue accessQueue;

    /**
     * 声明DirectExchange交换器--交换器是分发消息的组件
     *
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(Constants.DIRECT_EXCHANGE);
    }

    /**
     * 订单-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingOrderForDirectExchange() {
        return BindingBuilder.bind(accessQueue.queueForOrder()).to(directExchange()).with(Constants.ROUTING_KEY_ORDER_DIRECT);
    }

    /**
     * 支付-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingPayForDirectExchange() {
        return BindingBuilder.bind(accessQueue.queueForPay()).to(directExchange()).with(Constants.ROUTING_KEY_PAY_DIRECT);
    }

    /**
     * 邮件-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingEmailForDirectExchange() {
        return BindingBuilder.bind(accessQueue.queueForEmail()).to(directExchange()).with(Constants.ROUTING_KEY_EMAIL_DIRECT);
    }

    /**
     * 短信-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingSmsForDirectExchange() {
        return BindingBuilder.bind(accessQueue.queueForSms()).to(directExchange()).with(Constants.ROUTING_KEY_SMS_DIRECT);
    }
}
