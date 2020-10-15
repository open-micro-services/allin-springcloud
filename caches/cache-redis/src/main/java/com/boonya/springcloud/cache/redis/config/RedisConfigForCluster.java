package com.boonya.springcloud.cache.redis.config;

import com.boonya.springcloud.cache.redis.condition.RedisClusterCondition;
import com.boonya.springcloud.cache.redis.condition.RedisEnableCondition;
import com.boonya.springcloud.cache.redis.condition.RedisJedisCondition;
import com.boonya.springcloud.cache.redis.condition.RedisLettuceCondition;
import com.boonya.springcloud.cache.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName: RedisConfigForCluster
 * @Description: TODO(功能说明 ： Redis集群配置)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 17:28
 */
@Configuration
@Conditional(value = {RedisEnableCondition.class,RedisClusterCondition.class})
@Primary
public class RedisConfigForCluster {

    @Value("${spring.redis.password}")
    private String password;


    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.cluster.max-redirects}")
    private Integer maxRedirects;

    /**
     * Redis集群客户端配置【 此种方式仅使用在没有密码的情况】
     *
     * @return JedisCluster
     */
    @Bean
    public JedisCluster jedisCluster() {
        String[] serverArray = clusterNodes.split(",");
        Set<HostAndPort> nodes = new HashSet<>();

        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));

        }
        // 此种方式仅使用在没有密码的情况
        JedisCluster jedisCluster = new JedisCluster(nodes, 10000);
        return jedisCluster;
    }

    /**
     * Redis集群的配置
     *
     * @return RedisClusterConfiguration
     */
    @Bean
    public RedisClusterConfiguration redisClusterConfiguration() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        //Set<RedisNode> clusterNodes
        String[] serverArray = clusterNodes.split(",");

        Set<RedisNode> nodes = new HashSet<RedisNode>();

        for (String ipPort : serverArray) {
            String[] ipAndPort = ipPort.split(":");
            nodes.add(new RedisNode(ipAndPort[0].trim(), Integer.valueOf(ipAndPort[1])));
        }
        redisClusterConfiguration.setClusterNodes(nodes);
        redisClusterConfiguration.setMaxRedirects(maxRedirects);
        redisClusterConfiguration.setPassword(password);
        return redisClusterConfiguration;
    }

    /**
     * 【JedisConnectionFactory】实例化 RedisTemplate 对象
     *
     * @param redisClusterConfiguration
     * @param jedisPoolConfig 继承自JedisPoolConfig
     * @return
     */
    @Bean
    @Conditional(RedisJedisCondition.class)
    public RedisTemplate<String, Object> redisTemplateByJedis(RedisClusterConfiguration redisClusterConfiguration,RedisJedisPoolConfig jedisPoolConfig) {
        return RedisUtil.getRedisTemplate(redisClusterConfiguration,jedisPoolConfig);
    }

    /**
     * 【LettuceConnectionFactory】实例化 RedisTemplate 对象
     *
     * @param redisClusterConfiguration
     * @param lettuceClientConfiguration LettuceClientConfiguration接口实现
     * @return
     */
    @Bean
    @Conditional(RedisLettuceCondition.class)
    public RedisTemplate<String, Object> redisTemplateByLettuce(RedisClusterConfiguration redisClusterConfiguration,RedisLettuceClientConfiguration lettuceClientConfiguration) {
        return RedisUtil.getRedisTemplate(redisClusterConfiguration,lettuceClientConfiguration);
    }
}
