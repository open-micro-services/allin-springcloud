package com.boonya.netty.rpc.business.model;

import lombok.Data;

/**
 * @author lw
 */
@Data
public class User {

    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
