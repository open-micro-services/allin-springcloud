package com.boonya.springcloud.cache.redis.controller;

import com.boonya.springcloud.cache.redis.condition.RedisSingleCondition;
import com.boonya.springcloud.cache.redis.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: MessageController
 * @Description: TODO(功能说明：Redis单节点测试接口)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 18:00
 */
@RestController
@RequestMapping("/redis")
@Conditional(RedisSingleCondition.class)
public class RedisSingleController {

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/single/save.do")
    public Message storeMessageBySingle(@RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("from") String from,@RequestParam("to") String to){
        String key=UUID.randomUUID().toString();
        Message message=new Message(key,title, content, "single", from, to,new Date());
        redisTemplate.opsForValue().set(key, message);
        return message;
    }

    @RequestMapping("/single/get.do")
    public Message storeMessageBySingle(@RequestParam("key") String key){
        Message message= (Message) redisTemplate.opsForValue().get(key);
        return message;
    }


}
