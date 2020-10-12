package com.boonya.springcloud.cache.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName: RedisJedisPoolConfig
 * @Description: TODO(功能说明：JedisPoolConfig配置)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/2 22:10
 */
@Component
public class RedisJedisPoolConfig extends JedisPoolConfig {

    @Value("${spring.redis.jedis.pool.max-idle:10}")
    private Integer maxIdle;

    @Value("${spring.redis.jedis.pool.min-idle:0}")
    private Integer minIdle;

    @Value("${spring.redis.jedis.pool.max-active:10}")
    private Integer maxTotal;

    @Value("${spring.redis.jedis.pool.max-wait:10000}")
    private long maxWaitMillis;

    @Value("${spring.redis.jedis.pool.minEvictableIdleTimeMillis:10000}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${spring.redis.jedis.pool.numTestsPerEvictionRun:1000}")
    private Integer numTestsPerEvictionRun;

    @Value("${spring.redis.jedis.pool.time-between-eviction-runs:30}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${spring.redis.jedis.pool.testOnBorrow:false}")
    private boolean testOnBorrow;

    @Value("${spring.redis.jedis.pool.testOnReturn:false}")
    private boolean testOnReturn;

    @Value("${spring.redis.jedis.pool.testWhileIdle:true}")
    private boolean testWhileIdle;

    public RedisJedisPoolConfig() {
        super();
    }

    @Override
    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    @Override
    public int getMaxTotal() {
        return maxTotal;
    }

    @Override
    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    @Override
    public int getMaxIdle() {
        return maxIdle;
    }

    @Override
    public void setMinIdle(int minIdle) {
       this.minIdle = minIdle;
    }

    @Override
    public int getMinIdle() {
        return minIdle;
    }

    @Override
    public void setMaxWaitMillis(long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }
    @Override
    public long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    @Override
    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    @Override
    public int getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    @Override
    public void setTestOnBorrow(boolean testOnBorrow) {
       this.testOnBorrow = testOnBorrow;
    }

    @Override
    public boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    @Override
    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    @Override
    public boolean getTestOnReturn() {
        return testOnReturn;
    }

    @Override
    public void setTestWhileIdle(boolean testWhileIdle) {
       this.testWhileIdle = testWhileIdle;
    }

    @Override
    public boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    @Override
    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
      this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    @Override
    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }
}
