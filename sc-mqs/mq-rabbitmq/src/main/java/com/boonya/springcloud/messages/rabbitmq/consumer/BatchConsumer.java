package com.boonya.springcloud.messages.rabbitmq.consumer;

import com.boonya.springcloud.messages.rabbitmq.util.Constants;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeUnit;

/**
 * @Copyright: 2019-2021
 * @FileName: BatchConsumer.java
 * @Author: PJL
 * @Date: 2020/4/23 18:40
 * @Description: RabbitMQ客户端方法批量消费
 */
@Slf4j
@Component
public class BatchConsumer {

    /**
     * 批量处理队列消息
     *
     * @param channel
     */
    @RabbitListener(queues = {Constants.QUEUE_BATCH})
    public void onMessage(Channel channel) {
        while (true) {
            try {
                GetResponse response = channel.basicGet(Constants.QUEUE_BATCH, true);
                // 客户端轮询结果处理
                if (null == response || response.getMessageCount()<1) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }
                // 数据对象反序列化
                ByteArrayInputStream bis = new ByteArrayInputStream(response.getBody());
                ObjectInputStream ois = new ObjectInputStream(bis);
                try {
                    Object object = ois.readObject();// 可强转需要的类型
                    log.info("批量消费消息:" + object);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                // 响应确认
                channel.basicAck(0, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
