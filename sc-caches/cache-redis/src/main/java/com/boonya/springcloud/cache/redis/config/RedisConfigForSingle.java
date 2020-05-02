package com.boonya.springcloud.cache.redis.config;

import com.boonya.springcloud.cache.redis.condition.RedisEnableCondition;
import com.boonya.springcloud.cache.redis.condition.RedisJedisCondition;
import com.boonya.springcloud.cache.redis.condition.RedisLettuceCondition;
import com.boonya.springcloud.cache.redis.condition.RedisSingleCondition;
import com.boonya.springcloud.cache.redis.utils.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName: RedisConfigForSingle
 * @Description: TODO(功能说明 ： Redis单机配置【注意：单点模式需要把cluster和sentinel配置注释掉)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 17:26
 */
@Configuration
@Conditional(value = {RedisEnableCondition.class,RedisSingleCondition.class})
@Primary
public class RedisConfigForSingle {


    /**
     *【LettuceConnectionFactory】实例化 RedisTemplate 对象
     *
     * @param lettuceConnectionFactory lettuce基于Netty高性能连接池可选连接工厂
     * @return
     */
    @Bean
    @Conditional(RedisLettuceCondition.class)
    public RedisTemplate<String, Object> redisTemplateByLettuce(LettuceConnectionFactory lettuceConnectionFactory) {
        return RedisUtil.getRedisTemplate(lettuceConnectionFactory);
    }

    /**
     * 【JedisConnectionFactory】实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean
    @Conditional(RedisJedisCondition.class)
    public RedisTemplate<String, Object> redisTemplateByJedis(JedisConnectionFactory jedisConnectionFactory) {
        return RedisUtil.getRedisTemplate(jedisConnectionFactory);
    }

}
