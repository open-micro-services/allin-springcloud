package com.boonya.springcloud.cache.redis.controller;

import com.alibaba.fastjson.JSONObject;
import com.boonya.springcloud.cache.redis.condition.RedisClusterCondition;
import com.boonya.springcloud.cache.redis.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: MessageController
 * @Description: TODO(功能说明：Redis集群测试接口)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 18:00
 */
@RestController
@RequestMapping("/redis")
@Conditional(RedisClusterCondition.class)
public class RedisClusterController {

    /**
     * REDIS开启密码模式
     */
    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/cluster/save.do")
    public Message storeMessageByCluster(@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("from") String from,@RequestParam("to") String to){
        String key=UUID.randomUUID().toString();
        Message message=new Message(key,title, content, "single", from, to,new Date());
        redisTemplate.opsForValue().set(key, message);
        return message;
    }

    @RequestMapping("/cluster/get.do")
    public Message storeMessageByCluster(@RequestParam("key") String key){
        Message message= (Message) redisTemplate.opsForValue().get(key);
        return message;
    }


}
