package com.boonya.springcloud.cache.redis.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @ClassName: RedissonSingleCondition
 * @Description: TODO(功能说明：Redisson云托管模式)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/4 1:02
 */
@Slf4j
public class RedissonReplicatedCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String condition = conditionContext.getEnvironment().getProperty("system.redis-redisson-mode");
        log.info("system.redis-redisson-mode={}",condition);
        return condition.equals("replicated");
    }
}