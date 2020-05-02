package com.boonya.springcloud.cache.redis.config;

import com.boonya.springcloud.cache.redis.condition.RedisEnableCondition;
import com.boonya.springcloud.cache.redis.condition.RedisJedisCondition;
import com.boonya.springcloud.cache.redis.condition.RedisLettuceCondition;
import com.boonya.springcloud.cache.redis.listener.RedisMessageListener;
import com.boonya.springcloud.cache.redis.utils.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @ClassName: RedisListenerConfig
 * @Description: TODO(功能说明：REDIS消息监听配置)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/2 21:30
 */
@Configuration
@Conditional(value = {RedisEnableCondition.class})
public class RedisListenerConfig {

    /**
     * 【LettuceConnectionFactory】描述：需要手动定义RedisMessageListenerContainer加入IOC容器【超时过期key需要RedisMessageListenerContainer】
     *
     * @return
     */
    @Bean
    @Conditional(RedisLettuceCondition.class)
    public RedisMessageListenerContainer redisMessageListenerContainerByLettuce(LettuceConnectionFactory lettuceConnectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(lettuceConnectionFactory);
        /**
         * 自定义监听主题(HTTP心跳接收完成)
         */
        container.addMessageListener(new RedisMessageListener(), new PatternTopic(Constants.USER_POSITION_UPLOAD));

        /**
         * KEY过期监听（REDIS WINDOWS版本和LINUX版本存在差异,  LINUX默认是开启了全局超时监听的）redis.io redis-5.0.4
         */
        // container.addMessageListener(new RedisKeyExpiredListener(container), new PatternTopic(aggregationRedisService.getCurrentExpiredKey()));
        return container;
    }

    /**
     * 【JedisConnectionFactory】描述：需要手动定义RedisMessageListenerContainer加入IOC容器【超时过期key需要RedisMessageListenerContainer】
     *
     * @return
     */
    @Bean
    @Conditional(RedisJedisCondition.class)
    public RedisMessageListenerContainer redisMessageListenerContainer(JedisConnectionFactory jedisConnectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory);
        /**
         * 自定义监听主题(HTTP心跳接收完成)
         */
        container.addMessageListener(new RedisMessageListener(), new PatternTopic(Constants.USER_POSITION_UPLOAD));

        /**
         * KEY过期监听（REDIS WINDOWS版本和LINUX版本存在差异,  LINUX默认是开启了全局超时监听的）redis.io redis-5.0.4
         */
        // container.addMessageListener(new RedisKeyExpiredListener(container), new PatternTopic(aggregationRedisService.getCurrentExpiredKey()));
        return container;
    }
}
