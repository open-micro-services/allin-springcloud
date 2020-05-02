package com.boonya.springcloud.cache.redis.listener;

import com.boonya.springcloud.cache.redis.condition.RedisEnableCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RedisMessageListener
 * @Description: TODO(功能说明：REDIS消息监听)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/2 16:02
 */
@Slf4j
@Component
@Conditional(value = {RedisEnableCondition.class})
public class RedisMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        String msg = new String( message.getBody());
        String topic = new String(message.getChannel());
        System.out.println("REDIS监听消息:"+topic+",收到消息："+msg);
    }
}

