package com.boonya.springcloud.cache.redis.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @ClassName: RedisEnableCondition
 * @Description: TODO(功能说明：REDIS是否启用条件)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/2 20:26
 */
@Slf4j
public class RedisEnableCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String condition = conditionContext.getEnvironment().getProperty("system.redis-enable");
        log.info("system.redis-enable={}", condition);
        return condition.equals("true");
    }
}

