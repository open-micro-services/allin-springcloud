package com.boonya.springcloud.messages.rabbitmq.util;

/**
 * @Copyright: 2019-2021
 * @FileName: StatusCode.java
 * @Author: PJL
 * @Date: 2020/4/20 14:36
 * @Description: 系统服务状态码定义
 */
public enum StatusCode {
    /**
     * 成功
     */
    SUCCESS(200,"成功"),
    /**
     * 上线
     */
    ONLINE(201,"上线"),
    /**
     * 工作中
     */
    ONLINE_WITH_POSITION(202,"巡护中"),
    /**
     * 订阅消息
     */
    MESSAGE(203,"订阅消息"),
    /**
     * 失败
     */
    FAILED(400,"失败"),
    /**
     * 离线
     */
    OFFLINE(401,"离线"),
    /**
     * 超时
     */
    TIMEOUT(402,"超时"),
    /**
     * 错误
     */
    ERROR(403,"错误"),
    /**
     * 已支付
     */
    PAID(8000,"已支付"),
    /**
     * 未支付
     */
    UNPAID(8001,"未支付"),
    ;

    private int value;

    private String message;

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public void setValue(int value) {
        this.value = value;

    }

    StatusCode(int value, String message){
        this.value = value;
        this.message = message;
    }

}
