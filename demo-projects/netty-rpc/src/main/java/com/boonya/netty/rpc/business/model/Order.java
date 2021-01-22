package com.boonya.netty.rpc.business.model;

import lombok.Data;

/**
 * @author lw
 */
@Data
public class Order {

    private Integer id;
    private String name;
    private Integer userId;

    public Order(Integer id, String name, Integer userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }
}
