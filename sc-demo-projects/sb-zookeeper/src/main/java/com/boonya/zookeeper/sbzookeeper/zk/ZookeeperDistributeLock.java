package com.boonya.zookeeper.sbzookeeper.zk;

import org.I0Itec.zkclient.IZkDataListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

/**
 * @ClassName: ZookeeperDistributeLock
 * @Description: TODO(功能说明:ZK分布式锁（缺点锁竞争——羊群效应）)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/3/15 18:24
 */
public class ZookeeperDistributeLock extends AbstractZookeeperLock {

    private CountDownLatch countDownLatch=null;

    @Override
    public boolean getLock() {
        return super.getLock();
    }

    @Override
    public boolean tryLock() {
       try{
           zkClient.createEphemeral(LOCK_PATH);
           return true;
       }catch (Exception e){
           e.printStackTrace();
           return false;
       }
    }

    @Override
    public void waitLock() {
        IZkDataListener iZkDataListener=new IZkDataListener(){
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
                if(null!=countDownLatch){
                    countDownLatch.countDown();
                }
            }
        };
        // 注册监听
        zkClient.subscribeDataChanges(LOCK_PATH,iZkDataListener);

        if(zkClient.exists(LOCK_PATH)){
            countDownLatch=new CountDownLatch(1);
            try{
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //取消监听
        zkClient.unsubscribeDataChanges(LOCK_PATH,iZkDataListener);
    }

    @Override
    public void lock() {
        getLock();
    }

    @Override
    public void unlock() {
        zkClient.delete(LOCK_PATH);
        zkClient.close();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if(null!=countDownLatch&&countDownLatch.getCount()>0){
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
