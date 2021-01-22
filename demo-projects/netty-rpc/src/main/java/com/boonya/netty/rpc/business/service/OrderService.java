package com.boonya.netty.rpc.business.service;

import com.boonya.netty.rpc.business.model.Order;

public interface OrderService {

    Order findById(Integer id);

    Order findError();
}
