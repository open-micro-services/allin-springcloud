package com.boonya.springcloud.cache.redis.service;

import com.boonya.springcloud.cache.redis.condition.RedissonEnableCondition;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedissonLockService
 * @Description: TODO(功能说明：Redisson分布式锁服务【锁还有异步加锁和释放锁方式，这里只是方便直观的看锁的使用，不建议用此服务类】)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/3 22:49
 * @see "https://zhuanlan.zhihu.com/p/92144663"
 */
@Service
@Conditional(RedissonEnableCondition.class)
public class RedissonLockService {

    @Autowired
    private RedissonClient redissonClient;


    public RLock lock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.lock();
        return lock;
    }


    public RLock lock(String key, int leaseTime) {
        RLock lock = redissonClient.getLock(key);
        lock.lock(leaseTime, TimeUnit.SECONDS);
        return lock;
    }


    public RLock lock(String key, TimeUnit unit ,int timeout) {
        RLock lock = redissonClient.getLock(key);
        lock.lock(timeout, unit);
        return lock;
    }


    public boolean tryLock(String key, TimeUnit unit, int waitTime, int leaseTime) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }


    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }


    public void unlock(RLock lock) {
        lock.unlock();
    }

}
