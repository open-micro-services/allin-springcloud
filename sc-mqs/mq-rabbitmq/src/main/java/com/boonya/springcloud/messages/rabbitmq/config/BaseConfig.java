package com.boonya.springcloud.messages.rabbitmq.config;

import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

/**
 * @ClassName: BaseConfig
 * @Description: TODO(功能描述:RabbitMQ基础配置)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 12:15
 */
@Configuration
public class BaseConfig implements RabbitListenerConfigurer {

    /**
     * 消息处理方法注册到监听器
     *
     * @return
     */
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    /**
     * 消息处理方法工厂
     *
     * @return
     */
    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

    /**
     * 消费消息转换器
     *
     * @return
     */
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    /**
     * 定义消息转换实例 ，转化成 JSON传输--存在BUG【必须配置工厂方法MessageHandlerMethodFactory】
     *
     * @see com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.boonya.springcloud.messages.rabbitmq.bean.Order` (no Creators, like default construct, exist): no String-argument constructor/factory method to deserialize from String value ('{"discount":1.0,"id":"e05f98ab-45a9-40a9-89b4-1d4ebe4127ac","orderTime":1587637163144,"payStatus":8001,"productName":"羽绒服","productPrice":80,"transferCode":"7aa676b6-2867-4955-a9df-7044873e5aba"}')
     *  at [Source: (String)""{\"discount\":1.0,\"id\":\"e05f98ab-45a9-40a9-89b4-1d4ebe4127ac\",\"orderTime\":1587637163144,\"payStatus\":8001,\"productName\":\"羽绒服\",\"productPrice\":80,\"transferCode\":\"7aa676b6-2867-4955-a9df-7044873e5aba\"}""; line: 1, column: 1]
     *
     * @return Jackson2JsonMessageConverter
     */
   /* @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }*/

    /**
     * 配置启用rabbitmq事务
     *
     * @param connectionFactory
     * @return RabbitTransactionManager
     */
    /*@bean
    public RabbitTransactionManager rabbitTransactionManager(CachingConnectionFactory connectionFactory) {
        return new RabbitTransactionManager(connectionFactory);
    }*/

}
