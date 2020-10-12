package com.boonya.statemachine.bean;

import com.boonya.statemachine.config.States;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @ClassName: StateBean
 * @Description: TODO(功能说明：实体状态管理)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/12 16:46
 */
@WithStateMachine
@Data
@Slf4j
public class StateBean {

    /**
     * @see States
     */
    private String status = States.DRAFT.name();

    @OnTransition(target = "PUBLISH_TODO")
    public void online() {
        log.info("操作上线，待发布. target status:{}", States.PUBLISH_TODO.name());
        setStatus(States.PUBLISH_TODO.name());
    }

    @OnTransition(target = "PUBLISH_DONE")
    public void publish() {
        log.info("操作发布,发布完成. target status:{}", States.PUBLISH_DONE.name());
        setStatus(States.PUBLISH_DONE.name());
    }

    @OnTransition(target = "DRAFT")
    public void rollback() {
        log.info("操作回滚,回到草稿状态. target status:{}", States.DRAFT.name());
        setStatus(States.DRAFT.name());
    }
}
