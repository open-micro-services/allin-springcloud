package com.boonya.springcloud.messages.rocketmq.advance;

import com.boonya.springcloud.messages.rocketmq.util.JsonUtil2;

/**
 * 封装消息处理类
 * @param <T>
 */
public interface MessageProcessor<T> {

    boolean handleMessage(T message);

    Class<T> getClazz();

    default T transferMessage(String message) {
        return JsonUtil2.fromJson(message, getClazz());
    }
}
