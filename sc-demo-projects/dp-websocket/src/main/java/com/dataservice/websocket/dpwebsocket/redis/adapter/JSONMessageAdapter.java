package com.dataservice.websocket.dpwebsocket.redis.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JSONMessageAdapter {

    private final static Logger logger = LoggerFactory.getLogger(JSONMessageAdapter.class);

    public void   handle(String message){
        logger.info("StringMessageAdapter:handle(message)");
    }
}
