package com.dataservice.websocket.dpwebsocket.redis.adapter;

import com.dataservice.websocket.dpwebsocket.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class StringMessageAdapter {

    private final static Logger logger = LoggerFactory.getLogger(StringMessageAdapter.class);

    public void   handle(String message){
       logger.info("StringMessageAdapter:handle(message)");
    }
}
