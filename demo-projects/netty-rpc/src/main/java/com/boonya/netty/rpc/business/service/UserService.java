package com.boonya.netty.rpc.business.service;


import com.boonya.netty.rpc.business.model.User;

/**
 * @author lw
 */
public interface UserService {

    /**
     * find by id
     * @param id id
     * @return user
     */
    User findById(Integer id);
}
