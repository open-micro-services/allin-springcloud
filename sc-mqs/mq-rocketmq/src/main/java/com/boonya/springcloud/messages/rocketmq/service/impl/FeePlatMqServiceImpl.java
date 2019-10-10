package com.boonya.springcloud.messages.rocketmq.service.impl;

import com.boonya.springcloud.messages.rocketmq.service.FeePlatMqService;
import com.boonya.springcloud.messages.rocketmq.service.ParamConfigService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class FeePlatMqServiceImpl implements FeePlatMqService {
    @Resource
    private DefaultMQProducer defaultMQProducer;
    @Resource
    private ParamConfigService paramConfigService ;
    @Override
    public SendResult openAccountMsg(String msgInfo) {
        // 可以不使用Config中的Group
        defaultMQProducer.setProducerGroup(paramConfigService.feePlatGroup);
        SendResult sendResult = null;
        try {
            Message sendMsg = new Message(paramConfigService.feePlatTopic,
                paramConfigService.feeAccountTag,
                "fee_open_account_key", msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult ;
    }
}
