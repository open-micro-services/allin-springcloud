package com.boonya.springcloud.cache.redis.bean;

/**
 * @ClassName: ZsetParam
 * @Description: TODO(功能说明：ZSET类型)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/25 0:32
 */
public class ZsetParam extends HashParam {

    private  long score;

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }
}
