package com.boonya.springcloud.messages.rabbitmq.controller;

import com.alibaba.fastjson.JSONObject;
import com.boonya.springcloud.messages.rabbitmq.bean.Order;
import com.boonya.springcloud.messages.rabbitmq.producer.AmqpProducer;
import com.boonya.springcloud.messages.rabbitmq.util.Constants;
import com.boonya.springcloud.messages.rabbitmq.util.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @Copyright: 2019-2021
 * @FileName: BatchPublishController.java
 * @Author: PJL
 * @Date: 2020/4/24 9:22
 * @Description: 批量发布和消费控制器
 */
@Slf4j
@RestController
@RequestMapping("/rabbitmq/batch")
public class BatchPublishController {

    @Autowired
    AmqpProducer amqpProducer;

    ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 通过TopicExchange发送订单消息【异步调用无返回值】
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/topic/order.do")
    public void sendOrderByTopic(HttpServletRequest request, HttpServletResponse response) {
        log.info("get into api:{}", request.getMethod());
        Runnable runnable = (() -> {
            for (int i = 0; i < 10; i++) {
                String id = UUID.randomUUID().toString();
                String transferCode = UUID.randomUUID().toString();
                BigDecimal productPrice = new BigDecimal(i * 10 + 8);
                Order order = new Order(id, "羽绒服", productPrice, transferCode, 1.0, StatusCode.UNPAID.getValue(), new Date());
                String json = JSONObject.toJSONString(order);
                log.info("send message:{}", json);
                MessageProperties messageProperties = new MessageProperties();
                messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
                //messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
                Message message = new Message(json.getBytes(), messageProperties);
                amqpProducer.send(Constants.TOPIC_EXCHANGE, Constants.ROUTING_KEY_BATCH_TOPIC, message);
                amqpProducer.convertAndSend(Constants.TOPIC_EXCHANGE, Constants.ROUTING_KEY_BATCH_TOPIC, json);
                amqpProducer.convertSendAndReceive(Constants.TOPIC_EXCHANGE, Constants.ROUTING_KEY_BATCH_TOPIC, json);
            }
        });
        // 异步调用必须要自己执行
        executorService.execute(runnable);
    }

    /**
     * 通过DirectExchange发送订单消息【异步调用无返回值】
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/direct/order.do")
    public StatusCode sendOrderByDirect(HttpServletRequest request, HttpServletResponse response) {
        log.info("get into api:{}", request.getMethod());
        Callable callable = (() -> {
            for (int i = 0; i < 10; i++) {
                String id = UUID.randomUUID().toString();
                String transferCode = UUID.randomUUID().toString();
                BigDecimal productPrice = new BigDecimal(i * 10 + 8);
                Order order = new Order(id, "羽绒服", productPrice, transferCode, 1.0, StatusCode.UNPAID.getValue(), new Date());
                String json = JSONObject.toJSONString(order);
                log.info("send message:{}", json);
                MessageProperties messageProperties = new MessageProperties();
                messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
                //messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
                Message message = new Message(json.getBytes(), messageProperties);
                amqpProducer.send(Constants.DIRECT_EXCHANGE, Constants.ROUTING_KEY_BATCH_DIRECT, message);
                amqpProducer.convertAndSend(Constants.DIRECT_EXCHANGE, Constants.ROUTING_KEY_BATCH_DIRECT, json);
                amqpProducer.convertSendAndReceive(Constants.DIRECT_EXCHANGE, Constants.ROUTING_KEY_BATCH_DIRECT, json);
            }
            return StatusCode.SUCCESS;
        });
        // 异步调用必须要自己执行
        Future<StatusCode> future = executorService.submit(callable);
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
