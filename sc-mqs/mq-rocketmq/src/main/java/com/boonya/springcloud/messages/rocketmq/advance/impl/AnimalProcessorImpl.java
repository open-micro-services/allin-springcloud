package com.boonya.springcloud.messages.rocketmq.advance.impl;

import com.boonya.springcloud.messages.rocketmq.advance.MessageProcessor;
import com.boonya.springcloud.messages.rocketmq.advance.bean.Panda;
import org.springframework.stereotype.Service;

@Service
public class AnimalProcessorImpl implements MessageProcessor<Panda> {

    @Override
    public boolean handleMessage(Panda message) {
        System.out.println("animal receive:" + message.toString());
        return true;
    }

    @Override
    public Class<Panda> getClazz() {
        return Panda.class;
    }
}
