package com.boonya.springcloud.cache.redis.config;

import com.boonya.springcloud.cache.redis.condition.RedisClusterCondition;
import com.boonya.springcloud.cache.redis.condition.RedisEnableCondition;
import com.boonya.springcloud.cache.redis.condition.RedisJedisCondition;
import com.boonya.springcloud.cache.redis.condition.RedisLettuceCondition;
import com.boonya.springcloud.cache.redis.listener.RedisMessageListener;
import com.boonya.springcloud.cache.redis.utils.Constants;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.resource.ClientResources;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import java.time.Duration;
import java.util.HashSet;
import java.util.Optional;
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

    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private Integer minIdle;

    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer maxTotal;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private Integer maxWaitMillis;

    @Value("${spring.redis.jedis.pool.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${spring.redis.jedis.pool.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Value("${spring.redis.jedis.pool.time-between-eviction-runs}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${spring.redis.jedis.pool.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.redis.jedis.pool.testWhileIdle}")
    private boolean testWhileIdle;

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
     * JedisPoolConfig 连接池
     *
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(maxIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(maxTotal);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        // 逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // 每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        jedisPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        // 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        // 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        // 在空闲时检查有效性, 默认false
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        return jedisPoolConfig;
    }

    /**
     * Lettuce 客户端配置
     *
     * @return
     */
    @Bean
    public LettuceClientConfiguration lettuceClientConfiguration(){
        LettuceClientConfiguration lettuceClientConfiguration = new LettuceClientConfiguration() {
            @Override
            public boolean isUseSsl() {
                return false;
            }

            @Override
            public boolean isVerifyPeer() {
                return false;
            }

            @Override
            public boolean isStartTls() {
                return false;
            }

            @Override
            public Optional<ClientResources> getClientResources() {
                return Optional.empty();
            }

            @Override
            public Optional<ClientOptions> getClientOptions() {
                return Optional.empty();
            }

            @Override
            public Optional<String> getClientName() {
                return Optional.empty();
            }

            @Override
            public Optional<ReadFrom> getReadFrom() {
                return Optional.empty();
            }

            @Override
            public Duration getCommandTimeout() {
                return null;
            }

            @Override
            public Duration getShutdownTimeout() {
                return null;
            }

            @Override
            public Duration getShutdownQuietPeriod() {
                return null;
            }
        };
        return lettuceClientConfiguration;
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
     * 【JedisConnectionFactory】实例化 RedisTemplate 对象
     *
     * @param redisClusterConfiguration
     * @param jedisPoolConfig
     * @return
     */
    @Bean
    @Conditional(RedisJedisCondition.class)
    public RedisTemplate<String, Object> redisTemplateByJedis(RedisClusterConfiguration redisClusterConfiguration,JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
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

    /**
     * 【LettuceConnectionFactory】实例化 RedisTemplate 对象
     *
     * @param redisClusterConfiguration
     * @param lettuceClientConfiguration
     * @return
     */
    @Bean
    @Conditional(RedisLettuceCondition.class)
    public RedisTemplate<String, Object> redisTemplateByLettuce(RedisClusterConfiguration redisClusterConfiguration,LettuceClientConfiguration lettuceClientConfiguration) {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
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
}
