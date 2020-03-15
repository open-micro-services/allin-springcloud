package com.boonya.zookeeper.sbzookeeper.zk;

import com.boonya.zookeeper.sbzookeeper.lock.AbstractLock;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: AbstractZookeeperLock
 * @Description: TODO(功能说明:zk抽象锁)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/3/15 18:14
 */
public abstract class AbstractZookeeperLock extends AbstractLock {

    /**
     * ZK连接地址
     */
    @Value("{spring.zookeeper.address}")
    private String zkAddress;

    /**
     * ZK连接客户端对象
     */
    protected ZkClient zkClient=new ZkClient(null==zkAddress?"127.0.0.1:2181":zkAddress);


    public  static final String LOCK_PATH="/lock";

    public  static final String LOCK_PATH2="/lock2";

}
