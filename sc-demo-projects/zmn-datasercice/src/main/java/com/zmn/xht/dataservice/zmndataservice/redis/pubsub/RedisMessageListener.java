package com.zmn.xht.dataservice.zmndataservice.redis.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class RedisMessageListener implements MessageListener {

    private final static Logger logger = LoggerFactory.getLogger(RedisMessageListener.class);

    /**
     * 订阅接收发布者的消息
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] channel = message.getChannel();
        byte[] body = message.getBody();
        String msg = new String(body);
        String topic = new String(channel);
        logger.info("RedisMessageListener:onMessage 发布订阅监听,channel="+topic+",收到消息："+msg);
    }
}
