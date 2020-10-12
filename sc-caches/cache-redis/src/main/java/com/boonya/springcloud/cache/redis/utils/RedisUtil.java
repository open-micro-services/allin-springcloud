package com.boonya.springcloud.cache.redis.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: RedisUtil
 * @Description: TODO(功能说明：REDIS工具类)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/2 21:44
 */
public class RedisUtil {


    /**
     * 设置RedisTemplate
     *
     * @param redisSentinelConfiguration
     * @param jedisPoolConfig
     * @return
     */
    public static RedisTemplate<String, Object> getRedisTemplate(RedisSentinelConfiguration redisSentinelConfiguration, JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration, jedisPoolConfig);
        return getRedisTemplate(jedisConnectionFactory);
    }

    /**
     * 设置RedisTemplate
     *
     * @param redisSentinelConfiguration
     * @param lettuceClientConfiguration
     * @return
     */
    public static RedisTemplate<String, Object> getRedisTemplate(RedisSentinelConfiguration redisSentinelConfiguration, LettuceClientConfiguration lettuceClientConfiguration){
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisSentinelConfiguration, lettuceClientConfiguration);
        return getRedisTemplate(lettuceConnectionFactory);
    }

    /**
     * 设置RedisTemplate
     *
     * @param redisClusterConfiguration
     * @param jedisPoolConfig
     * @return
     */
    public static RedisTemplate<String, Object> getRedisTemplate(RedisClusterConfiguration redisClusterConfiguration, JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
        return getRedisTemplate(jedisConnectionFactory);
    }

    /**
     * 设置RedisTemplate
     *
     * @param redisClusterConfiguration
     * @param lettuceClientConfiguration
     * @return
     */
    public static RedisTemplate<String, Object> getRedisTemplate(RedisClusterConfiguration redisClusterConfiguration, LettuceClientConfiguration lettuceClientConfiguration){
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
        return getRedisTemplate(lettuceConnectionFactory);
    }

    /**
     * 设置RedisTemplate
     *
     * @param redisConnectionFactory
     * @return
     */
    public static RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
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
     * 获取Jedis线程池配置
     *
     * @return
     */
    public static JedisPoolConfig getJedisPoolConfig(
        Integer maxIdle,Integer maxTotal, Integer maxWaitMillis,Integer minEvictableIdleTimeMillis,
        Integer numTestsPerEvictionRun,long timeBetweenEvictionRunsMillis,boolean testOnBorrow,boolean testWhileIdle){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }
}
