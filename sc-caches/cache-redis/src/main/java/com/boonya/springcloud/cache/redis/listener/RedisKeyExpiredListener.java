package com.boonya.springcloud.cache.redis.listener;

import com.boonya.springcloud.cache.redis.condition.RedisEnableCondition;
import com.boonya.springcloud.cache.redis.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @ClassName: RedisKeyExpiredListener
 * @Description: TODO(功能说明：REDIS过期KEY监听)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/2 16:09
 */
@Slf4j
@Component
@Conditional(value = {RedisEnableCondition.class})
public class RedisKeyExpiredListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String( message.getChannel());
        String key = new String( message.getBody());
        // 用户位置过期监听处理
        if(key.startsWith(Constants.USER_POSITION_TIMEOUT)){
            log.info("监听过期channel="+channel+",过期KEY："+key);
            key = key.replace(Constants.USER_POSITION_TIMEOUT,"");
            String userId = key.split(":")[0];
            // 执行用户下线
            //MobileServiceDataQueue.addToOfflineQueue(userId);
        }
    }
}

