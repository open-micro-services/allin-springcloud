package com.dataservice.websocket.dpwebsocket.controller;

import com.dataservice.websocket.dpwebsocket.redis.RedisChannel;
import com.dataservice.websocket.dpwebsocket.redis.RedisService;
import com.dataservice.websocket.dpwebsocket.redis.mq.MQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping
public class RedisController {

    @Autowired
    RedisService redisService;

    @RequestMapping("/pubsub/userChannel/{message}")
    @ResponseBody
    public String publishUserMessage(@PathVariable("message") String message){
        redisService.publish(RedisChannel.USER_CHANNEL.getValue(),message);
        return "success";
    }

    @RequestMapping("/pubsub/eventChannel/{message}")
    @ResponseBody
    public String publishEventMessage(@PathVariable("message") String message){
        redisService.publish(RedisChannel.EVENT_CHANNEL.getValue(),message);
        return "success";
    }


    @RequestMapping("/redismq/publish/{message}")
    @ResponseBody
    public String publishMQMessage(@PathVariable("message") String message){
        // 未初始化初始化队列
        redisService.initRedisMq();
        // 将消息放入队列
        MQProducer.produce(message);
        return "success";
    }

    @RequestMapping("/redismq/publish/channel/{message}")
    @ResponseBody
    public String publishMQWithChannelMessage(@PathVariable("message") String message){
        // 未初始化初始化队列
        redisService.initRedisMq();
        // 将消息放入队列
        MQProducer.produce(RedisChannel.TEST_CHANNEL.getValue(),message);
        return "success";
    }
}
