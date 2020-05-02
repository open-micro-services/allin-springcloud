package com.boonya.springcloud.cache.redis.config;

import com.boonya.springcloud.cache.redis.condition.RedisEnableCondition;
import com.boonya.springcloud.cache.redis.condition.RedisJedisCondition;
import com.boonya.springcloud.cache.redis.condition.RedisLettuceCondition;
import com.boonya.springcloud.cache.redis.condition.RedisSentinelCondition;
import com.boonya.springcloud.cache.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: RedisConfigForSentinel
 * @Description: TODO(功能说明：Redis哨兵配置)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 17:39
 */
@Configuration
@Conditional(value = {RedisEnableCondition.class,RedisSentinelCondition.class})
@Primary
public class RedisConfigForSentinel {

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.sentinel.master}")
    private String master;

    @Value("${spring.redis.sentinel.nodes}")
    private String clusterNodes;

    /**
     * Redis集群的配置
     *
     * @return RedisClusterConfiguration
     */
    @Bean
    public RedisSentinelConfiguration redisSentinelConfiguration() {
        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
        //Set<RedisNode> sentinelNodes
        String[] serverArray = clusterNodes.split(",");

        Set<RedisNode> nodes = new HashSet<RedisNode>();

        for (String ipPort : serverArray) {
            String[] ipAndPort = ipPort.split(":");
            nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
        }
        redisSentinelConfiguration.setSentinels(nodes);
        redisSentinelConfiguration.setMaster(master);
        redisSentinelConfiguration.setPassword(password);
        return redisSentinelConfiguration;
    }

    /**
     * 【JedisConnectionFactory】实例化 RedisTemplate 对象
     *
     * @param redisSentinelConfiguration
     * @param jedisPoolConfig 继承自JedisPoolConfig
     * @return
     */
    @Bean
    @Conditional(RedisJedisCondition.class)
    public RedisTemplate<String, Object> redisTemplateByJedis(RedisSentinelConfiguration redisSentinelConfiguration, RedisJedisPoolConfig jedisPoolConfig) {
       return RedisUtil.getRedisTemplate(redisSentinelConfiguration,jedisPoolConfig);
    }

    /**
     * 【LettuceConnectionFactory】实例化 RedisTemplate 对象
     *
     * @param redisSentinelConfiguration
     * @param redisLettuceClientConfiguration LettuceClientConfiguration接口实现
     * @return
     */
    @Bean("redisTemplateByLettuce")
    @Conditional(RedisLettuceCondition.class)
    public RedisTemplate<String, Object> redisTemplateByLettuce(RedisSentinelConfiguration redisSentinelConfiguration, RedisLettuceClientConfiguration redisLettuceClientConfiguration) {
        return RedisUtil.getRedisTemplate(redisSentinelConfiguration,redisLettuceClientConfiguration);
    }

}
