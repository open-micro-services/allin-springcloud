package com.boonya;

import com.boonya.netty.rpc.core.netty.server.RpcNettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 不使用 spring boot web，启动 netty server 进行监听
 *
 * @author lw https://github.com/lw1243925457/JAVA-000/tree/main/homework/rpc/rpc-demo
 */
@SpringBootApplication
@Slf4j
public class ServerApplication implements ApplicationRunner {

    @Qualifier("rpcNettyServerInstance")
    @Autowired
    private final RpcNettyServer rpcNettyServer;

    public ServerApplication(RpcNettyServer rpcNettyServer) {
        this.rpcNettyServer = rpcNettyServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            rpcNettyServer.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rpcNettyServer.destroy();
        }
    }
}
