package com.boonya;

import com.boonya.netty.rpc.business.model.Order;
import com.boonya.netty.rpc.business.model.User;
import com.boonya.netty.rpc.business.service.OrderService;
import com.boonya.netty.rpc.business.service.UserService;
import com.boonya.netty.rpc.core.proxy.RpcByteBuddy;
import com.boonya.netty.rpc.core.proxy.RpcClient;
import com.boonya.netty.rpc.core.proxy.RpcClientJdk;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ClientApplication {

    public static void main(String[] args) {

        RpcClient jdk = new RpcClientJdk();
        UserService userService = jdk.create(UserService.class, "http://localhost:8080/");
        User user = userService.findById(1);
        if (user == null) {
            log.info("Clint service invoke Error");
            return;
        }
        System.out.println("find user id=1 from server: " + user.getName());

        RpcClient buddy = new RpcByteBuddy();
        OrderService orderService = buddy.create(OrderService.class, "http://localhost:8080/");
        Order order = orderService.findById(1992129);
        if (order == null) {
            log.info("Clint service invoke Error");
            return;
        }
        System.out.println(String.format("find order name=%s, user=%d",order.getName(),order.getUserId()));

//        order = orderService.findError();
//        if (order == null) {
//            log.info("Clint service invoke Error");
//        }
    }

}
