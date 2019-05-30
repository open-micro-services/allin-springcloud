package com.dataservice.websocket.dpwebsocket.redis.pubsub;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
public class RedisMessageListener implements MessageListener {

    /**
     * 订阅接收发布者的消息
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] channel = message.getChannel();
        byte[] body = message.getBody();
        String msg = new String(body);
        String topic = new String(channel);
        System.out.println("发布订阅监听channel="+topic+",收到消息："+msg);
    }
}
