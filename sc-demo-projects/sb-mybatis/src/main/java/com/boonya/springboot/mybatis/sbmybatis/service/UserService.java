package com.boonya.springboot.mybatis.sbmybatis.service;

import com.boonya.springboot.mybatis.sbmybatis.entity.User;

public interface UserService {

    boolean deleteByPrimaryKey(Integer id);

    boolean insert(User user);

    boolean insertSelective(User user);

    User selectByPrimaryKey(Integer id);

    boolean updateByPrimaryKeySelective(User user);

    boolean updateByPrimaryKey(User user);
}
