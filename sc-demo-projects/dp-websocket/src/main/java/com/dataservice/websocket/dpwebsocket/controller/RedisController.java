package com.dataservice.websocket.dpwebsocket.controller;

import com.dataservice.websocket.dpwebsocket.redis.RedisChannel;
import com.dataservice.websocket.dpwebsocket.redis.RedisService;
import com.dataservice.websocket.dpwebsocket.redis.mq.MQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping
public class RedisController {

    @Autowired
    RedisService redisService;

    @Autowired
    RedisTemplate redisTemplate;


    /**
     * 发布订阅消息
     * @param message
     * @return
     */
    @RequestMapping("/pubsub/userChannel/{message}")
    @ResponseBody
    public String publishUserMessage(@PathVariable("message") String message){
        redisService.publish(RedisChannel.USER_CHANNEL.getValue(),message);
        return "success";
    }

    /**
     * 发布订阅消息
     * @param message
     * @return
     */
    @RequestMapping("/pubsub/eventChannel/{message}")
    @ResponseBody
    public String publishEventMessage(@PathVariable("message") String message){
        redisService.publish(RedisChannel.EVENT_CHANNEL.getValue(),message);
        return "success";
    }

    /**
     * 队列发布消息
     * @param message
     * @return
     */
    @RequestMapping("/redismq/publish/{message}")
    @ResponseBody
    public String publishMQMessage(@PathVariable("message") String message){
        // 将消息放入队列
        //MQProducer.produce(message);
        redisService.produce(message);
        return "success";
    }

    /**
     * 队列发布消息
     * @param message
     * @return
     */
    @RequestMapping("/redismq/publish/channel/{message}")
    @ResponseBody
    public String publishMQWithChannelMessage(@PathVariable("message") String message){
        // 将消息放入队列
        //MQProducer.produce(RedisChannel.TEST_CHANNEL.getValue(),message);
        redisService.produce(RedisChannel.TEST_CHANNEL.getValue(),message);
        return "success";
    }

    @RequestMapping("/redis/transaction/userCount")
    @ResponseBody
    public String redisTransaction(int value){
        String key="user_count";
        try{
            // 开启事务支持
            redisTemplate.setEnableTransactionSupport(true);
            // 监视数据key
            redisTemplate.watch(key);
            // 开启事务
            redisTemplate.multi();
            // 预处理执行语句
            redisTemplate.opsForSet().add(key,value);
            // 执行事务处理
            redisTemplate.exec();
        }catch(Exception e){
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }
}
