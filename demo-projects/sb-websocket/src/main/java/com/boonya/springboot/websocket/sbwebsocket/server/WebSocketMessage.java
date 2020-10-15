package com.boonya.springboot.websocket.sbwebsocket.server;

import java.io.Serializable;

/**
 * WebSocket消息类
 */
public class WebSocketMessage implements Serializable {

    public int status = 0;

    private String message = "";

    private Object data = new Object();

    public WebSocketMessage(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        if (data != null) {
            this.data = data;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
