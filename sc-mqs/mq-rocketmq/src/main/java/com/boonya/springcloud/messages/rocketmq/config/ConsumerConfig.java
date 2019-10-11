package com.boonya.springcloud.messages.rocketmq.config;

import com.boonya.springcloud.messages.rocketmq.advance.exception.RocketMQException;
import com.boonya.springcloud.messages.rocketmq.listener.RocketMsgListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;

/**
 * RocketMQ 消费者配置
 */
@Configuration
public class ConsumerConfig {
    private static final Logger LOG = LoggerFactory.getLogger(ConsumerConfig.class) ;
    @Value("${rocketmq.common.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.common.consumer.groupName}")
    private String groupName;
    @Value("${rocketmq.common.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.common.consumer.consumeThreadMax}")
    private int consumeThreadMax;
    @Value("${rocketmq.common.consumer.topics}")
    private String topics;
    @Value("${rocketmq.common.consumer.consumeMessageBatchMaxSize}")
    private int consumeMessageBatchMaxSize;
    @Resource
    private RocketMsgListener msgListener;

    /**
     * 此方法名字不能与其他@Bean定义方法重复，比如
     * @see com.boonya.springcloud.messages.rocketmq.advance.consumer.Consumer
     * @return
     * @throws RocketMQException
     */
    @Bean
    public DefaultMQPushConsumer getRocketMQConsumer(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.registerMessageListener(msgListener);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        try {
            String[] topicTagsArr = topics.split(";");
            for (String topicTags : topicTagsArr) {
                String[] topicTag = topicTags.split("~");
                consumer.subscribe(topicTag[0],topicTag[1]);
            }
            consumer.start();
        }catch (MQClientException e){
            e.printStackTrace();
        }
        return consumer;
    }
}
