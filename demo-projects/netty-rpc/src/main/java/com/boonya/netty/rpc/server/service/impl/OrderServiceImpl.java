package com.boonya.netty.rpc.server.service.impl;


import com.boonya.netty.rpc.business.model.Order;
import com.boonya.netty.rpc.business.service.OrderService;
import com.boonya.netty.rpc.core.exception.CustomException;

public class OrderServiceImpl implements OrderService {

    @Override
    public Order findById(Integer id) {
        return new Order(1, "RPC", 1);
    }

    @Override
    public Order findError() {
        throw new CustomException("Custom exception");
    }
}
