package com.boonya.statemachine.command;

import com.boonya.statemachine.config.Events;
import com.boonya.statemachine.config.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Component;

/**
 * @ClassName: StartupRunner
 * @Description: TODO(功能说明：测试状态机)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/12 15:11
 */
@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    StateMachine<States, Events> stateMachine;

    @Override
    public void run(String... args) throws Exception {
        stateMachine.start();
        stateMachine.sendEvent(Events.ONLINE);
        stateMachine.sendEvent(Events.PUBLISH);
        stateMachine.sendEvent(Events.ROLLBACK);
    }
}
