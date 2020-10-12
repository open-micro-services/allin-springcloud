package com.boonya.springcloud.cache.redis.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @ClassName: RedisSingleCondition
 * @Description: TODO(功能说明：REDIS单点模式条件)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/2 14:49
 */
@Slf4j
public class RedisSingleCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String condition = conditionContext.getEnvironment().getProperty("system.redis-mode");
        log.info("system.redis-mode={}",condition);
        return condition.equals("single");
    }
}
