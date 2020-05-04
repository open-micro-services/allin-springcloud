package com.boonya.springcloud.cache.redis.controller;

import com.boonya.springcloud.cache.redis.condition.RedissonEnableCondition;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedissonLockController
 * @Description: TODO(功能说明：Redisson分布式锁测试)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/5/3 22:15
 */
@RestController
@RequestMapping("/redisson")
@Conditional(RedissonEnableCondition.class)
public class RedissonLockController {

    @Autowired
    RedissonClient redissonClient;

    /**
     *分布式锁测试
     *
     * @return
     */
    @RequestMapping("/tryLock.do")
    public ResponseEntity<String> tryLock(@RequestParam("userId") String userId){
        RLock lock = redissonClient.getLock(userId);
        boolean locked = false;
        try {
            // 三秒时间获取分布式锁
            locked = lock.tryLock(3,TimeUnit.SECONDS);
            if(locked){
                // 修改业务数据.............
            }else{
                return ResponseEntity.ok("false");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(locked){
                lock.unlock();
            }
        }
        return ResponseEntity.ok("true");
    }

    /**
     *分布式锁测试
     *
     * @return
     */
    @RequestMapping("/lock.do")
    public ResponseEntity<String> lock(@RequestParam("userId") String userId){
        RLock lock = redissonClient.getLock(userId);
        boolean locked = false;
        try {
            if(!lock.isLocked()){
                lock.lock();
                locked=true;
                // 修改业务数据.............
            }else{
                return ResponseEntity.ok("false");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(locked){
                lock.unlock();
            }
        }
        return ResponseEntity.ok("true");
    }

    /**
     *分布式锁测试
     *
     * @return
     */
    @RequestMapping("/lockAsync")
    public ResponseEntity<String> lockAsync(@RequestParam("userId") String userId){
        RLock lock = redissonClient.getLock(userId);
        boolean locked = false;
        RFuture future = null;
        try {
            future = lock.lockAsync();
            // 三秒时间获取分布式锁
            Object result = future.get(3, TimeUnit.SECONDS);
            // 修改业务数据.............
            if(null != result){
                locked=true;
            }else{
                return ResponseEntity.ok("false");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(locked){
                lock.unlockAsync();
            }
        }
        return ResponseEntity.ok("true");
    }
}
