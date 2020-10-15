package com.boonya.springcloud.cache.redis.config;

import com.boonya.springcloud.cache.redis.condition.*;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import java.io.IOException;

/**
 * @ClassName: RedissonConfig
 * @Description: TODO(功能说明：Redisson多模式配置)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/3 23:49
 */
@Configuration
@Conditional(RedissonEnableCondition.class)
public class RedissonConfig {

    /**
     * 【单点模式】
     *
     * @return
     * @throws IOException
     */
    @Bean
    @Conditional(value = {RedissonSingleCondition.class})
    public RedissonClient redissonForSingle() throws IOException {
        // 本例子使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-single.yml"));
        return Redisson.create(config);
    }

    /**
     * 【主从模式】
     *
     * @return
     * @throws IOException
     */
    @Bean
    @Conditional(value = {RedissonMasterSlaveCondition.class})
    public RedissonClient redissonForMasterSlave() throws IOException {
        // 本例子使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-master-slave.yml"));
        return Redisson.create(config);
    }

    /**
     * 【集群模式】
     *
     * @return
     * @throws IOException
     */
    @Bean
    @Conditional(value = {RedissonClusterCondition.class})
    public RedissonClient redissonForCluster() throws IOException {
        // 本例子使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-cluster.yml"));
        return Redisson.create(config);
    }

    /**
     * 【哨兵模式】
     *
     * @return
     * @throws IOException
     */
    @Bean
    @Conditional(value = {RedissonSentinelCondition.class})
    public RedissonClient redissonForSentinel() throws IOException {
        // 本例子使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-sentinel.yml"));
        return Redisson.create(config);
    }

    /**
     * 【云托管模式】
     *
     * @return
     * @throws IOException
     */
    @Bean
    @Conditional(value = {RedissonReplicatedCondition.class})
    public RedissonClient redissonForReplicated() throws IOException {
        // 本例子使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-replicated.yml"));
        return Redisson.create(config);
    }
}
