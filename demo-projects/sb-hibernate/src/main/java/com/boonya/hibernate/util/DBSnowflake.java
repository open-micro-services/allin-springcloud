package com.boonya.hibernate.util;

import io.shardingsphere.core.keygen.DefaultKeyGenerator;
import java.util.Random;
/**
 *
 * 类说明：<br>
 * 生成snowflake算法流水号
 *
 * CreateDate: 2019年10月09日
 */
public class DBSnowflake {

    private static final int SEED = 255;

    private static DefaultKeyGenerator defaultKeyGenerator = new DefaultKeyGenerator();

    static {
        DefaultKeyGenerator.setWorkerId(new Random().nextInt(SEED));
    }

    /**
     * 生成流水号
     * @return string
     */
    public static long getNextId() {
        return defaultKeyGenerator.generateKey().longValue();
    }
}
