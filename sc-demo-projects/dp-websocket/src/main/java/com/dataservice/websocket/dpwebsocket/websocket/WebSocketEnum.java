package com.dataservice.websocket.dpwebsocket.websocket;

/**
 * WebSocket枚举类型，推送以下类别的数据
 */
public enum WebSocketEnum {
    /**
     * 用户相关数量统计（设备、人员）
     */
    STATISTICS_USER("statistics_user"),
    /**
     * 事件相关数量统计（周期统计、周期列表）
     */
    STATISTICS_EVENTS("statistics_events");

    WebSocketEnum(String value){
        this.value = value;
    }

    private String value="";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
