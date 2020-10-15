package com.dataservice.websocket.dpwebsocket.redis;

/**
 * 定义Redis发布订阅通道
 */
public enum RedisChannel {
    /**
     * 用户通道
     */
    USER_CHANNEL("user_channel"),
    /**
     * 事件通道
     */
    EVENT_CHANNEL("event_channel"),
    /**
     * 测试通道
     */
    TEST_CHANNEL("test_channel");

    RedisChannel(String value){
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
