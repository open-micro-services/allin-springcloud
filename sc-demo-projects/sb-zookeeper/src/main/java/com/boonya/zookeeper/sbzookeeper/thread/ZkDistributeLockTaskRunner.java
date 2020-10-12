package com.boonya.zookeeper.sbzookeeper.thread;

import java.util.concurrent.locks.Lock;

/**
 * @ClassName: ZkDistributeLockTaskRunner
 * @Description: TODO(功能说明 : ZK分布式线程任务)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/3/15 20:26
 */
public class ZkDistributeLockTaskRunner implements Runnable {

    private static int sequenceNo = 0;

    private Lock lock = null;

    public ZkDistributeLockTaskRunner(Lock lock) {
        this.lock = lock;
    }

    public void getSequenceNo() {
        try {
            lock.lock();
            sequenceNo++;
            System.out.println("数字:" + sequenceNo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        getSequenceNo();
    }
}
