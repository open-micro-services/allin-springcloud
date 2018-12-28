package com.boonya.springcloud.messages.kafka.entity;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Message
 * @Description: TODO(消息实体)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 1:21
 */
public class Message implements Serializable{

    private String id;

    private String message;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    public Message(String id, String message, Date sendTime) {
        this.id = id;
        this.message = message;
        this.sendTime = sendTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
