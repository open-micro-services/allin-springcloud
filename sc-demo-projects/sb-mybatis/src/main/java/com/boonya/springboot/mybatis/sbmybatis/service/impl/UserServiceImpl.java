package com.boonya.springboot.mybatis.sbmybatis.service.impl;

import com.boonya.springboot.mybatis.sbmybatis.entity.User;
import com.boonya.springboot.mybatis.sbmybatis.mapper.UserMapper;
import com.boonya.springboot.mybatis.sbmybatis.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public boolean deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id)>0?true:false;
    }

    @Override
    public boolean insert(User user) {
        return userMapper.insert(user)>0?true:false;
    }

    @Override
    public boolean insertSelective(User user) {
        return userMapper.insertSelective(user)>0?true:false;
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user)>0?true:false;
    }

    @Override
    public boolean updateByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKey(user)>0?true:false;
    }
}
