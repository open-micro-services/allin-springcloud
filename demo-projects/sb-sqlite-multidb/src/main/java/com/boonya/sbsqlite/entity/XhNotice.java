package com.boonya.sbsqlite.entity;



public class XhNotice {
    // *****************************************属性*************************************//
    // //标题、内容、日期、来源（发送人）、是否已读

    // {"msg_type":2,"msg_priority":0,"msg_content":"12333","msg_time":"2015-07-27 15:39:23","sender":"李彦洋"}
    // @Column(column = Constances.XH_NOTICE_TYPE)
    // private String msg_type;// 通知公告类型-

    private long id;//ID 编号自增

    private String msg_content;// 通知公告内容 - 通知公告的内容

    private String msg_priority;// 通知公告紧急级别，0-普通，1-紧急

    private String msg_time;// 通知公告时间 - 2015-07-15_14:00:00

    private String sender;// 通知公告来源 - XX人的人名，或单位名称


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMsg_content() {
        return msg_content;
    }

    public void setMsg_content(String msg_content) {
        this.msg_content = msg_content;
    }

    public String getMsg_priority() {
        return msg_priority;
    }

    public void setMsg_priority(String msg_priority) {
        this.msg_priority = msg_priority;
    }

    public String getMsg_time() {
        return msg_time;
    }

    public void setMsg_time(String msg_time) {
        this.msg_time = msg_time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}