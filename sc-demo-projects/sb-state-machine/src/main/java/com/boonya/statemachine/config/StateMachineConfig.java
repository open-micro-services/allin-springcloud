package com.boonya.statemachine.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import java.util.EnumSet;

/**
 * @ClassName: StateMachineConfig
 * @Description: TODO(功能说明)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/12 14:50
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates().initial(States.DRAFT).states(EnumSet.allOf(States.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions.withExternal()
            // 草稿
            .source(States.DRAFT).target(States.PUBLISH_TODO)
            .event(Events.ONLINE)
            .and()
            // 发布
            .withExternal()
            .source(States.PUBLISH_TODO).target(States.PUBLISH_DONE)
            .event(Events.PUBLISH)
            .and()
            // 回滚
            .withExternal()
            .source(States.PUBLISH_DONE).target(States.DRAFT)
            .event(Events.ROLLBACK);
    }
}
