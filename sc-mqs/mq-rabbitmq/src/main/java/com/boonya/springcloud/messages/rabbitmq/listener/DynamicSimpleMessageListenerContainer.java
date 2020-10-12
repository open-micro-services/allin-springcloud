package com.boonya.springcloud.messages.rabbitmq.listener;

import com.boonya.springcloud.messages.rabbitmq.config.AccessQueue;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessageHandler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Copyright: 2019-2021
 * @FileName: DynamicSimpleMessageListenerContainer.java
 * @Author: PJL
 * @Date: 2020/4/23 12:50
 * @Description: 动态简单消息监听容器
 */
@Slf4j
@Configuration
public class DynamicSimpleMessageListenerContainer {

    @Value("${spring.rabbitmq.listener.concurrency:10}")
    Integer concurrency;

    @Value("${spring.rabbitmq.listener.max-concurrency:20}")
    Integer maxConcurrency;

    @Autowired
    AccessQueue accessQueue;

    /**
     * 消息监听容器【可动态管理队列】
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        MessageListenerAdapter adapter = new MessageListenerAdapter(new Consumer() {
            @Override
            public void handleConsumeOk(String s) {

            }

            @Override
            public void handleCancelOk(String s) {

            }

            @Override
            public void handleCancel(String s) throws IOException {

            }

            @Override
            public void handleShutdownSignal(String s, ShutdownSignalException e) {

            }

            @Override
            public void handleRecoverOk(String s) {

            }

            @Override
            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {

            }
        });
        /**
         * 设置处理器的消费消息的默认方法,如果没有设置，那么默认的处理器中的默认方式是handleMessage方法
         */
        adapter.setDefaultListenerMethod("onMessage");
        container.setConnectionFactory(connectionFactory);
        /**
         * 创建需要的队列
         */
        container.addQueues(accessQueue.queueForDebug(),accessQueue.queueForError(),accessQueue.queueForInfo(),accessQueue.queueForDelete());
        /**
         * SimpleMessageListenerContainer可以监听多个队列，container.setQueueNames的api接收的是一个字符串数组对象
         */
        container.setQueueNames("debug.queue","error.queue","info.queue","delete.queue");
        /**
         * 动态删除监听队列名称(不会删除真实的队列)
         */
        container.removeQueueNames("delete.queue");
        /**
         * 遍历队列实例
         */
        String [] names= container.getQueueNames();
        for (String name: names){
            log.info(">>>>>>>>>>>>>>>>>队列实例:{}",name);
        }
        /**
         * 设置消息监听处理
         */
        container.setMessageListener((message)  -> {
            log.info("====接收到"+message.getMessageProperties().getConsumerQueue()+"队列的消息=====");
            log.debug(message.getMessageProperties().toString());
            log.info(new String(message.getBody()));
        });
        /**
         * 设置通道消息监听
         */
       /* container.setMessageListener(new ChannelAwareMessageListener() {
            public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
                byte[] body = message.getBody();
                String correlationId = message.getMessageProperties().getCorrelationId();
                log.info("correlationId={}",correlationId);
                //CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
                AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                    .correlationId(UUID.randomUUID().toString())
                    .replyTo("return")
                    .build();
                System.out.println(" getReplyRequest----"+props.getCorrelationId()+"==============" +new Date() );
                channel.basicPublish("", "return", props, body);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        });*/
        /**
         * 后置处理器，接收到的消息都添加了Header请求头
         */
        container.setAfterReceivePostProcessors((message) -> {
            message.getMessageProperties().getHeaders().put("desc",10);
            return message;
        });
        /**
         * 设置消费者的tag和args
         */
       /* //设置消费者的consumerTag_tag
        container.setConsumerTagStrategy(queue -> "order_queue_tag");
        //设置消费者的Arguments
        Map<String, Object> args = new HashMap<>();
        args.put("module","订单模块");
        args.put("fun","发送消息");
        container.setConsumerArguments(args);*/
        /**
         * 设置消费的并发处理属性
         */
        container.setConcurrentConsumers(concurrency);
        container.setMaxConcurrentConsumers(maxConcurrency);
        /**
         * 是否重回队列
         */
        container.setDefaultRequeueRejected(false);
        /**
         * ACK签收模式
         */
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        /**
         * 暴露监听通道
         */
        container.setExposeListenerChannel(true);
        return container;
    }
}
