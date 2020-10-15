package com.boonya.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName: ReentrantReadWriteLockExample
 * @Description: TODO(功能说明：读写锁是读和写方面有侧重的时候使用，基于数据库读写分离)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/4 16:54
 */
public class ReentrantReadWriteLockExample {

    /**
     * 读写锁(默认是非公平锁,非公平锁效率优于公平锁)
     */
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock(false);
    /**
     * 读锁
     */
    private Lock readLock = readWriteLock.readLock();

    /**
     * 写锁
     */
    private Lock writeLock = readWriteLock.writeLock();


    /**
     * 写操作可以使用Condition等待通知
     */
    public void write(){
        writeLock.lock();
        System.out.println("write......locked");
        try{
            // 等待
            writeLock.newCondition().await(3, TimeUnit.SECONDS);
            // 编写业务逻辑
            System.out.println("write......");
            // 通知
            writeLock.newCondition().signal();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            writeLock.unlock();// 确保锁的释放
            System.out.println("write......unlocked");
        }
    }

    /**
     * 读操作不能使用Condition等待通知
     */
    public void read(){
        readLock.lock();
        System.out.println("read......locked");
        try{
            // 编写业务逻辑
            System.out.println("read......");
        }finally {
            readLock.unlock();// 确保锁的释放
            System.out.println("read......unlocked");
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockExample reentrantReadWriteLockExample = new ReentrantReadWriteLockExample();
        reentrantReadWriteLockExample.write();
        reentrantReadWriteLockExample.read();
    }


}
