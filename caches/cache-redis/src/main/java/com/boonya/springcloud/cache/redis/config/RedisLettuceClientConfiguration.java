package com.boonya.springcloud.cache.redis.config;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.resource.ClientResources;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

/**
 * @ClassName: RedisLettuceClientConfiguration
 * @Description: TODO(功能说明)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/2 21:32
 */
@Component
public class RedisLettuceClientConfiguration implements LettuceClientConfiguration {

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
}
