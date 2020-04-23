package com.boonya.springcloud.messages.rabbitmq.config;

import com.boonya.springcloud.messages.rabbitmq.util.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Copyright: 2019-2021
 * @FileName: FanoutExchangeConfig.java
 * @Author: PJL
 * @Date: 2020/4/21 18:52
 * @Description: TopicExchange交换器--广播模式（Broadcast）
 */
@Configuration
public class FanoutExchangeConfig {

    @Autowired
    AccessQueue accessQueue;

    /**
     * 声明交换器--交换器是分发消息的组件
     *
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(Constants.FANOUT_EXCHANGE);
    }

    /**
     * 订单-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingOrderForFanoutExchange() {
        return BindingBuilder.bind(accessQueue.queueForOrder()).to(fanoutExchange());
    }

    /**
     * 支付-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingPayForFanoutExchange() {
        return BindingBuilder.bind(accessQueue.queueForPay()).to(fanoutExchange());
    }

    /**
     * 邮件-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingEmailForFanoutExchange() {
        return BindingBuilder.bind(accessQueue.queueForEmail()).to(fanoutExchange());
    }

    /**
     * 短信-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingSmsForFanoutExchange() {
        return BindingBuilder.bind(accessQueue.queueForSms()).to(fanoutExchange());
    }

    /**
     * 位置-绑定交换器和路由键对应关系
     *
     * @return
     */
    @Bean
    public Binding bindingPositionForFanoutExchange() {
        return BindingBuilder.bind(accessQueue.queueForPosition()).to(fanoutExchange());
    }
}
