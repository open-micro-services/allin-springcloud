package com.boonya.zookeeper.sbzookeeper.zk;

import com.alibaba.fastjson.JSONObject;
import org.I0Itec.zkclient.IZkDataListener;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * @ClassName: ZookeeperDistributeLock
 * @Description: TODO(功能说明 : ZK分布式增强锁 ( 排队机制))
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/3/15 18:24
 */
public class ZookeeperDistributePlusLock extends AbstractZookeeperLock {

    private CountDownLatch countDownLatch = null;

    private String beforePath;//前一个序列路径

    private String currentPath;//当前序列路径

    public ZookeeperDistributePlusLock() {
        if (!zkClient.exists(LOCK_PATH2)) {
            zkClient.createPersistent(LOCK_PATH2);
        }
    }

    @Override
    public boolean getLock() {
        return super.getLock();
    }

    @Override
    public boolean tryLock() {
        if (null == currentPath || currentPath.length() <= 0) {
            currentPath = zkClient.createEphemeralSequential(LOCK_PATH2 + '/', "lock");
        }
        List<String> children = zkClient.getChildren(LOCK_PATH2);
        //集合排序
        Collections.sort(children);

        System.out.println("LIST===>" + JSONObject.toJSON(children));

        if (currentPath.equals(LOCK_PATH2 + '/' + children.get(0))) {
            //成功获取到锁
            return true;
        } else {
            //获取序列号的位置,序列号是7位
            int position = Collections.binarySearch(children, currentPath.substring(7));
            beforePath = LOCK_PATH2 + '/' + children.get(position - 1);
        }
        return false;
    }

    @Override
    public void waitLock() {
        IZkDataListener iZkDataListener = new IZkDataListener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                //注意这里放在数据被删除的方法处理，不要放在handleDataChange
                if (null != countDownLatch) {
                    countDownLatch.countDown();
                }
            }
        };
        // 注册监听
        zkClient.subscribeDataChanges(beforePath, iZkDataListener);

        if (zkClient.exists(beforePath)) {
            countDownLatch = new CountDownLatch(1);
            try {
                countDownLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //取消监听
        zkClient.unsubscribeDataChanges(beforePath, iZkDataListener);
    }

    @Override
    public void lock() {
        getLock();
    }

    @Override
    public void unlock() {
        zkClient.delete(currentPath);
        zkClient.close();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if (null != countDownLatch && countDownLatch.getCount() > 0) {
            countDownLatch.countDown();
        }
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
