package com.zmn.xht.dataservice.zmndataservice.redis.pubsub.config;

import com.zmn.xht.dataservice.zmndataservice.redis.RedisChannel;
import com.zmn.xht.dataservice.zmndataservice.redis.pubsub.RedisMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisPubsubConfig {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    /**
     * 描述：需要手动定义RedisMessageListenerContainer加入IOC容器
     * @return
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(jedisConnectionFactory);

        /**
         * 添加订阅者监听类，数量不限.PatternTopic定义监听主题,这里监听主题
         */
        container.addMessageListener(new RedisMessageListener(), new PatternTopic(RedisChannel.USER_CHANNEL.getValue()));
        container.addMessageListener(new RedisMessageListener(), new PatternTopic(RedisChannel.EVENT_CHANNEL.getValue()));
        return container;

    }
}
