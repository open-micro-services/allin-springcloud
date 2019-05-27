package com.boonya.springboot.websocket.sbwebsocket.service;

import com.boonya.springboot.websocket.sbwebsocket.entity.User;

public interface UserService {

    boolean deleteByPrimaryKey(Integer id);

    boolean insert(User user);

    boolean insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(User user);

    boolean updateByPrimaryKey(User user);
}
