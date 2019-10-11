package com.boonya.springcloud.messages.rocketmq.advance.consumer;

import com.boonya.springcloud.messages.rocketmq.advance.exception.RocketMQException;
import com.boonya.springcloud.messages.rocketmq.advance.impl.AnimalProcessorImpl;
import com.boonya.springcloud.messages.rocketmq.advance.impl.UserProcessorImpl;
import com.boonya.springcloud.messages.rocketmq.advance.listener.MessageListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    @Autowired
    private UserProcessorImpl messageProcessor;
    @Autowired
    private AnimalProcessorImpl animalProcessor;

    @Value("${rocketmq.advance.consumer.namesrvAddr}")
    private String namesrvAddr;
    @Value("${rocketmq.advance.consumer.groupName}")
    private String groupName;
    @Value("${rocketmq.advance.consumer.topic}")
    private String topic;
    @Value("${rocketmq.advance.consumer.tag}")
    private String tag;
    @Value("${rocketmq.advance.consumer.consumeThreadMin}")
    private int consumeThreadMin;
    @Value("${rocketmq.advance.consumer.consumeThreadMax}")
    private int consumeThreadMax;
    //新增了下面两个配置
    @Value("${rocketmq.advance.consumer.animal.topic}")
    private String animalTopic;
    @Value("${rocketmq.advance.consumer.animal.tag}")
    private String animalTag;

    /**
     * 此方法名字不能与其他@Bean定义方法重复，比如
     * @see com.boonya.springcloud.messages.rocketmq.config.ConsumerConfig
     * @return
     * @throws RocketMQException
     */
    @Bean
    public DefaultMQPushConsumer getAdvanceRocketMQConsumer() throws RocketMQException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setVipChannelEnabled(false);
        MessageListener messageListene = new MessageListener();
        //在监听类中增加两个消息处理类，当然可以增加更多，也就是往MessageListen类中的map集合放入处理类。
        messageListene.registerHandler(tag, messageProcessor);
        messageListene.registerHandler(animalTag,animalProcessor);
        consumer.registerMessageListener(messageListene);
        try {
            consumer.subscribe(topic,this.tag);
            //这里需要多加一个设置。第二个参数是tag表示只会消费topic下面标签为tag的消息，如果是* 就表示会消费tapic下面所有的消息。
            consumer.subscribe(animalTopic,"*");
            consumer.start();
            log.info("consumer is start !!! groupName:{},topic:{},namesrvAddr:{}",groupName,topic,namesrvAddr);
        }catch (MQClientException e){
            log.error("consumer is start !!! groupName:{},topic:{},namesrvAddr:{}",groupName,topic,namesrvAddr,e);
            throw new RocketMQException(e.getMessage());
        }
        return consumer;
    }
}
