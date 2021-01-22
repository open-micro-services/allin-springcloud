package com.boonya.socket.rpc;

import java.util.Map;

public interface IMessage {

    /**
     * 获取消息
     *
     * @param object
     * @return
     */
    Map<String, Object> getMessage(Object object);
}
