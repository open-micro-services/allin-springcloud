package com.dataservice.websocket.dpwebsocket.websocket;

import java.io.Serializable;

/**
 * WebSocket消息类
 */
public class WebSocketMessage implements Serializable {

    public int status = 0;

    private String message = "";

    private Object data = null;

    public WebSocketMessage(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        if (data != null) {
            this.data = data;
        }else{
            this.data= new Object();
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
