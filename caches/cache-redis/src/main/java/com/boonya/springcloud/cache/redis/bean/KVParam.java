package com.boonya.springcloud.cache.redis.bean;

/**
 * @ClassName: KVParam
 * @Description: TODO(功能说明：KV-STRING类型)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/25 0:24
 */
public class KVParam {

    private String key;

    private String value;

    private long timeout = -1;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
