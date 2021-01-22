package com.boonya.netty.rpc.business.service;

import com.boonya.netty.rpc.business.model.Order;

/**
 * @author lw
 */
public interface OrderService {

    /**
     * find by id
     * @param id id
     * @return order
     */
    Order findById(Integer id);

    /**
     * return exception
     * @return exception
     */
    Order findError();
}
