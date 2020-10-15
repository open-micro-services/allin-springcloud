package com.zmn.xht.dataservice.zmndataservice.redis;

import com.zmn.xht.dataservice.zmndataservice.redis.mq.MQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/**
 * Redis工具服务
 */
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redis的消息队列直接使用redis数组实现
     */
    private static ListOperations<String, String> redisList =null;

    /**
     * 获取队列数据对象
     * @return
     */
    public static ListOperations<String, String> getRedisMQ() {
        return redisList;
    }

    /**
     * 初始化redis队列对象
     */
    @PostConstruct
    private void initRedisMQList(){
        if(redisList==null){
            redisList=redisTemplate.opsForList();
        }
    }

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }
    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }
    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        Object result = null;
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        if(result==null){
            return null;
        }
        return result.toString();
    }
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * hset设置数据
     * @param key
     * @param value
     * @return
     */
    public  boolean hset(String key, Map<String, String> value) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().putAll(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * hset设置数据
     * @param key
     * @param field
     * @param value
     * @return
     */
    public  boolean hset(String key,String field, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().put(key,field,value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * hset删除数据
     * @param key
     * @param filed
     * @return
     */
    public  boolean hdelete(String key, String filed) {
        boolean result = false;
        try {
            redisTemplate.opsForHash().delete(key,filed);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取hset的kv对象
     * @param key
     * @return
     */
    public  Map<String,String> entries(String key) {
        Map<String,String> result =null;
        try {
            result=  redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发布主题消息
     * @param channel
     * @param message
     */
    public  void publish(String channel,String message) {
        try {
            redisTemplate.convertAndSend(channel,message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布队列消息
     * @param message
     */
    public  void produce(String message) {
        try {
            // 将消息放入队列
            MQProducer.produce(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布队列消息
     * @param channel
     * @param message
     */
    public  void produce(String channel,String message) {
        try {
            // 将消息放入队列
            MQProducer.produce(channel,message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
