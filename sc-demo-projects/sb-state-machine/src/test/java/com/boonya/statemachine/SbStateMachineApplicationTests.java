package com.boonya.statemachine;

import com.boonya.statemachine.bean.StateBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SbStateMachineApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void state() {
        StateBean stateBean = new StateBean();
        stateBean.publish();
        log.info("publish=="+stateBean.getStatus());
        stateBean.online();
        log.info("online=="+stateBean.getStatus());
        stateBean.rollback();
        log.info("rollback=="+stateBean.getStatus());
    }

}
