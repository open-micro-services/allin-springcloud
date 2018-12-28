package com.boonya.springcloud.cache.redis.entity;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Message
 * @Description: TODO(消息实体类)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 17:57
 */
public class Message implements Serializable{

    private String id;

    private String title;

    private String content;

    private String note;

    private String from;

    private String to;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date date;

    public Message() {
    }

    public Message(String id, String title, String content, String note, String from, String to, Date date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.note = note;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
