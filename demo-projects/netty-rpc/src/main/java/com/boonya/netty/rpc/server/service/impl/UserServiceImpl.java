package com.boonya.netty.rpc.server.service.impl;


import com.boonya.netty.rpc.business.model.User;
import com.boonya.netty.rpc.business.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public User findById(Integer id) {
        return new User(id, "RPC");
    }
}
