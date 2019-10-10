package com.boonya.springcloud.messages.rocketmq.advance.impl;

import com.boonya.springcloud.messages.rocketmq.advance.MessageProcessor;
import com.boonya.springcloud.messages.rocketmq.advance.bean.User;
import org.springframework.stereotype.Service;

@Service
public class UserProcessorImpl implements MessageProcessor<User> {

    @Override
    public boolean handleMessage(User user) {
        System.out.println("user receive : " + user.toString());
        return true;
    }

    @Override
    public Class<User> getClazz() {
        return User.class;
    }
}
