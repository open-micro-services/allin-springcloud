package com.boonya.zookeeper.sbzookeeper.lock;

import java.util.concurrent.locks.Lock;

/**
 * @ClassName: AbstractLock
 * @Description: TODO(功能说明 ： 抽象锁)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/3/15 18:04
 */
public abstract class AbstractLock implements Lock {

    /**
     * 获取锁
     *
     * @return
     */
    public boolean getLock() {
        if (tryLock()) {
            return true;
        } else {
            waitLock();
            return getLock();
        }
    }

    /**
     * 尝试获取锁
     */
    public abstract boolean tryLock();

    /**
     * 等待锁
     */
    public abstract void waitLock();
}
