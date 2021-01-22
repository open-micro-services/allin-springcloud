package com.boonya.netty.rpc.server.config;

import com.boonya.netty.rpc.business.service.OrderService;
import com.boonya.netty.rpc.business.service.UserService;
import com.boonya.netty.rpc.core.netty.server.RpcNettyServer;
import com.boonya.netty.rpc.server.service.impl.OrderServiceImpl;
import com.boonya.netty.rpc.server.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置接口的实现类
 *
 * @author lw
 */
@Configuration
public class BeanConfig {

    @Bean("rpcNettyServerInstance")
    public RpcNettyServer rpcNettyServer(ApplicationContext context) {
        return new RpcNettyServer(context);
    }

    @Bean("com.boonya.netty.rpc.business.service.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean("com.boonya.netty.rpc.business.service.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
