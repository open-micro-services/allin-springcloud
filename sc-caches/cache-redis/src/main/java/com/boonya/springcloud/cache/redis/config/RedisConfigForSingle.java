package com.boonya.springcloud.cache.redis.config;

import com.boonya.springcloud.cache.redis.condition.RedisJedisCondition;
import com.boonya.springcloud.cache.redis.condition.RedisLettuceCondition;
import com.boonya.springcloud.cache.redis.condition.RedisSingleCondition;
import com.boonya.springcloud.cache.redis.listener.RedisMessageListener;
import com.boonya.springcloud.cache.redis.utils.Constants;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @ClassName: RedisConfigForSingle
 * @Description: TODO(功能说明 ： Redis单机配置【注意：单点模式需要把cluster和sentinel配置注释掉)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 17:26
 */
@Configuration
@Conditional(RedisSingleCondition.class)
@Primary
public class RedisConfigForSingle {

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

    /**
     * REDIS 序列化器设置[平台RedisTemplate注入方式有一定问题]
     *
     * @param lettuceConnectionFactory lettuce基于Netty高性能连接池可选连接工厂
     * @return
     */
    @Bean
    @Conditional(RedisLettuceCondition.class)
    public RedisTemplate<String, Object> redisTemplateByLettuce(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 字符串类型序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // 设置value的序列化规则和 key的序列化规则
        //redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        //redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        //redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * REDIS 序列化器设置[平台RedisTemplate注入方式有一定问题]
     *
     * @return
     */
    @Bean
    @Conditional(RedisJedisCondition.class)
    public RedisTemplate<String, Object> redisTemplateByJedis(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        // 使用Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 字符串类型序列化器
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // 设置value的序列化规则和 key的序列化规则
        //redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        //redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        //redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        redisTemplate.setEnableDefaultSerializer(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}
