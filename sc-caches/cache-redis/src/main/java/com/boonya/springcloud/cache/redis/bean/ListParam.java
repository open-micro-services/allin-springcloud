package com.boonya.springcloud.cache.redis.bean;

/**
 * @ClassName: ListParam
 * @Description: TODO(功能说明)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/25 0:49
 */
public class ListParam extends KVParam {

    private boolean left;
    private long range = -1;

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public long getRange() {
        return range;
    }

    public void setRange(long range) {
        this.range = range;
    }
}
