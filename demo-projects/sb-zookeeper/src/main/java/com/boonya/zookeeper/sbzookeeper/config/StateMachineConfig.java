package com.boonya.zookeeper.sbzookeeper.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.ensemble.StateMachineEnsemble;
import org.springframework.statemachine.zookeeper.ZookeeperStateMachineEnsemble;

/**
 * @ClassName: StateMachineConfig
 * @Description: TODO(功能说明:Zookeeper分布式状态机配置)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/12 18:36
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends StateMachineConfigurerAdapter<String, String> {

    @Value("${spring.zookeeper.address}")
    private String zkServer;

    @Override
    public void configure(StateMachineConfigurationConfigurer<String, String> config) throws Exception {
        config.withDistributed().ensemble(stateMachineEnsemble());
    }

    @Override
    public void configure(StateMachineStateConfigurer<String, String> states)
        throws Exception {
        states
            .withStates()
            .initial("LOCKED")
            .state("UNLOCKED");
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<String, String> transitions)
        throws Exception {
        transitions
            .withExternal()
            .source("LOCKED")
            .target("UNLOCKED")
            .event("COIN")
            .and()
            .withExternal()
            .source("UNLOCKED")
            .target("LOCKED")
            .event("PUSH");
    }

    @Bean
    public StateMachineEnsemble<String,String> stateMachineEnsemble() throws Exception{
        return new ZookeeperStateMachineEnsemble<String, String>(curatorClient(),"/foo");
    }

    @Bean
    public CuratorFramework curatorClient() {
        CuratorFramework client = CuratorFrameworkFactory.builder().defaultData(new byte[0])
            .retryPolicy(new ExponentialBackoffRetry(1000,3))
            .connectString(zkServer).build();
        client.start();
        return client;
    }
}
