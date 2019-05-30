package com.boonya.springcloud.cache.redis.controller;

import com.boonya.springcloud.cache.redis.config.RedisConfigForCluster;
import com.boonya.springcloud.cache.redis.config.RedisConfigForSentinel;
import com.boonya.springcloud.cache.redis.config.RedisConfigForSingle;
import com.boonya.springcloud.cache.redis.entity.Message;
import com.boonya.springcloud.cache.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: MessageController
 * @Description: TODO(Redis消息存储接口)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 18:00
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisConfigForSingle single;

    @Autowired
    RedisConfigForCluster cluster;

    @Autowired
    RedisConfigForSentinel sentinel;

//    @RequestMapping("/single/{title}/{content}/{from}/{to}")
//    public Message storeMessageBySingle(@PathVariable("title") String title,@PathVariable("content") String content,@PathVariable("from") String from,@PathVariable("to") String to){
//        RedisUtil util=single.redisUtil(redisTemplate);
//        String key=UUID.randomUUID().toString();
//        Message message=new Message(key,title, content, "single", from, to,new Date());
//        util.set(key,message,6000);
//        return message;
//    }
//
//    @RequestMapping("/single/{key}")
//    public Message storeMessageBySingle(@PathVariable("key") String key){
//        RedisUtil util=single.redisUtil(redisTemplate);
//        Message message= (Message) util.get(key);
//        return message;
//    }

    @RequestMapping("/cluster/{title}/{content}/{from}/{to}")
    public Message storeMessageByCluster(@PathVariable("title") String title, @PathVariable("content") String content, @PathVariable("from") String from, @PathVariable("to") String to) {
        RedisUtil util = cluster.redisUtil(redisTemplate);
        String key = UUID.randomUUID().toString();
        Message message = new Message(key, title, content, "cluster", from, to, new Date());
        util.set(key, message, 6000);
        return message;
    }

    @RequestMapping("/cluster/{key}")
    public Message storeMessageByCluster(@PathVariable("key") String key) {
        RedisUtil util = cluster.redisUtil(redisTemplate);
        Message message = (Message) util.get(key);
        return message;
    }

//    @RequestMapping("/sentinel/{title}/{content}/{from}/{to}")
//    public Message storeMessageBySentinel(@PathVariable("title") String title,@PathVariable("content") String content,@PathVariable("from") String from,@PathVariable("to") String to){
//        RedisUtil util=sentinel.redisUtil(redisTemplate);
//        String key=UUID.randomUUID().toString();
//        Message message=new Message(key,title, content, "sentinel", from, to,new Date());
//        util.set(key,message,6000);
//        return message;
//    }
//
//    @RequestMapping("/sentinel/{key}")
//    public Message storeMessageBySentinel(@PathVariable("key") String key){
//        RedisUtil util=sentinel.redisUtil(redisTemplate);
//        Message message= (Message) util.get(key);
//        return message;
//    }


}
