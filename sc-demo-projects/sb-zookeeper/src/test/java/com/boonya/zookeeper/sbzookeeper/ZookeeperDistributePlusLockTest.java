package com.boonya.zookeeper.sbzookeeper;

import com.boonya.zookeeper.sbzookeeper.thread.ZkDistributeLockTaskRunner;
import com.boonya.zookeeper.sbzookeeper.zk.ZookeeperDistributePlusLock;

/**
 * @ClassName: ZookeeperDistributePlusLockTest
 * @Description: TODO(功能说明：ZK分布式锁测试)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/3/15 20:33
 */
public class ZookeeperDistributePlusLockTest {

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            Thread thread=new Thread(new ZkDistributeLockTaskRunner(new ZookeeperDistributePlusLock()));
            thread.start();
        }
    }
}
