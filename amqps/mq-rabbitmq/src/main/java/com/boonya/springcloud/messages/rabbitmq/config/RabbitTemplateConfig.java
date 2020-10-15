package com.boonya.springcloud.messages.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Copyright: 2019-2021
 * @FileName: RabbitTemplateConfig.java
 * @Author: PJL
 * @Date: 2020/4/23 13:33
 * @Description: Rabbit消息队列配置【设置消息确认和返回】
 */
@Slf4j
@Configuration
public class RabbitTemplateConfig {

    /**
     * 回调函数: confirm确认
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData: " + correlationData);
            log.info("ack: " + ack);
            if(!ack){
                log.info("异常处理....");
            }
        }
    };

    /**
     * 回调函数: return返回
     */
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode,
                                    String replyText, String exchange, String routingKey) {
            log.info("return exchange: {}, routingKey: {}, replyCode: {}, replyText: {}",
                exchange, routingKey, replyCode, replyText);
        }
    };

    /**
     * 设置返回回调和确认回调
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.setConnectionFactory(connectionFactory);
        /**
         * 消息转化格式处理
         */
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
