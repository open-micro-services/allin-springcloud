package com.boonya.springcloud.cache.redis.bean;

/**
 * @ClassName: HashParam
 * @Description: TODO(功能说明：HASH类型)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/25 0:29
 */
public class HashParam extends KVParam {

    private String identify;

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }
}
