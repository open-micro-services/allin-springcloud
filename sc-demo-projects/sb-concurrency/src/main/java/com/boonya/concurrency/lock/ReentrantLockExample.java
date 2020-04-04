package com.boonya.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: ReentrantLockExample
 * @Description: TODO(功能说明：显示锁之可重入锁(可重入是指递归累加的效果是支持的))
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/4 16:25
 */
public class ReentrantLockExample {

    /**
     * 可重入锁(默认是非公平锁,非公平锁效率优于公平锁)
     */
    private Lock lock = new ReentrantLock(false);

    /**
     * 使用Condition配合Lock实现通知等待
     */
    private Condition lockCondition = lock.newCondition();

    public void doSomething() {
        lock.lock();
        System.out.println("doSomething......locked");
        try {
            // 等待
            lockCondition.await(3, TimeUnit.SECONDS);
            // 编写业务逻辑
            System.out.println("doSomething......");
            // 通知
            lockCondition.signalAll();
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();// 确保锁的释放
            System.out.println("doSomething......unlocked");
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();
        reentrantLockExample.doSomething();
    }

}
