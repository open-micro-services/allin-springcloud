package com.boonya.concurrency.cas;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: CasAtomicLock
 * @Description: TODO(功能说明 : CAS原子锁 利用自旋方式循环获取锁直到成功 ， 利用现代处理器原理)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/1 22:24
 */
public class CasAtomicLock {

    /**
     * 原子类型：AtomicInteger,AtomicLong,AtomicBoolean....
     * 原子引用类型：AtomicReference....
     * 原子数组:AtomicArray....
     */
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 函数入口
     *
     * @param args
     */
    public static void main(String[] args) {
        CasAtomicLock cas = new CasAtomicLock();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                cas.saveData();
            }).start();
        }
    }

    /**
     * 业务CAS操作
     */
    public void saveData() {
        while (true) {
            int count = atomicInteger.get();
            // CAS 锁获取 Compare and Swap
            boolean success = atomicInteger.compareAndSet(count, ++count);
            // 一定要得到锁，否则誓不罢休
            if (success) {
                //执行逻辑处理
                System.out.println("成功获取到锁，执行CAS逻辑处理....");
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("成功获取到锁，执行CAS逻辑处理....成功!");
                // 退出自旋
                break;
            } else {
                System.out.println("获取锁失败!");
            }

        }
    }

}
