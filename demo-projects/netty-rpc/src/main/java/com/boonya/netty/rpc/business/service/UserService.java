package com.boonya.netty.rpc.business.service;


import com.boonya.netty.rpc.business.model.User;

public interface UserService {

    User findById(Integer id);
}
