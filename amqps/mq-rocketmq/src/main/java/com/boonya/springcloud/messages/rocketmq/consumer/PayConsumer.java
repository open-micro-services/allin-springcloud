package com.boonya.springcloud.messages.rocketmq.consumer;

import com.boonya.springcloud.messages.rocketmq.config.JmsConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;
import java.io.UnsupportedEncodingException;

@Component
public class PayConsumer {
    private DefaultMQPushConsumer consumer;

    private String consumerGroup = "pay_consumer_group";

    public PayConsumer() throws MQClientException {
        consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(JmsConfig.NAME_SERVER);
        // 设置消费地点,从最后一个进行消费(其实就是消费策略)
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        // 订阅主题的哪些标签
        consumer.subscribe(JmsConfig.TOPIC, "*");
        // 注册监听器
        consumer.registerMessageListener((MessageListenerConcurrently)
            (msgs, context) -> {
                try {
                    // 获取Message
                    Message msg = msgs.get(0);
                    System.out.printf("%s Receive New Messages: %s %n",
                        Thread.currentThread().getName(), new String(msgs.get(0).getBody()));
                    String topic = msg.getTopic();
                    String body = new String(msg.getBody(), "utf-8");
                    // 标签
                    String tags = msg.getTags();
                    String keys = msg.getKeys();
                    System.out.println("topic=" + topic + ", tags=" + tags + ",keys=" + keys + ", msg=" + body);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            });
        consumer.start();
        System.out.println("Consumer Listener");
    }
}
