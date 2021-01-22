package com.boonya.socket.rpc.impl;

import com.boonya.socket.rpc.IMessage;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright: 2019-2021
 * @FileName: SimpleMessage.java
 * @Author: PJL
 * @Date: 2021/1/21 11:58
 * @Description: 消息实现
 */
@Service
public class SimpleMessage implements IMessage {

    @Override
    public Map<String, Object> getMessage(Object msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("msgType", "RPC");
        return map;
    }
}
