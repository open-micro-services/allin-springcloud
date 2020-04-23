package com.boonya.springcloud.messages.rabbitmq.controller;

import com.alibaba.fastjson.JSONObject;
import com.boonya.springcloud.messages.rabbitmq.Bean.Order;
import com.boonya.springcloud.messages.rabbitmq.producer.AmqpProducer;
import com.boonya.springcloud.messages.rabbitmq.util.Constants;
import com.boonya.springcloud.messages.rabbitmq.util.StatusCode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * @ClassName: AmqpPublishController
 * @Description: TODO(RabbitMQ消息发布订阅控制接口 - - 接口异步调用 ， 否则违背了RabbitMQ的高性能吞吐设计)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 11:53
 */
@RestController
@RequestMapping("/rabbitmq/amqp")
public class OrderPublishController {

    @Autowired
    AmqpProducer amqpProducer;

    /**
     * 通过TopicExchange发送订单消息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/topic/order.do")
    public Callable<StreamingResponseBody> sendOrderByTopic(HttpServletRequest request, HttpServletResponse response) {
        String id = UUID.randomUUID().toString();
        String transferCode = UUID.randomUUID().toString();
        BigDecimal productPrice = new BigDecimal(80);
        Order order = new Order(id,"羽绒服",productPrice,transferCode,1.0,StatusCode.UNPAID.getValue(),new Date());
        String json = JSONObject.toJSONString(order);
        return () -> {
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            //messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
            Message message = new Message(json.getBytes(),messageProperties);
            amqpProducer.send(Constants.TOPIC_EXCHANGE,Constants.ROUTING_KEY_ORDER_TOPIC,message);
            amqpProducer.convertAndSend(Constants.TOPIC_EXCHANGE,Constants.ROUTING_KEY_ORDER_TOPIC,json);
            amqpProducer.convertSendAndReceive(Constants.TOPIC_EXCHANGE,Constants.ROUTING_KEY_ORDER_TOPIC,json);
            return (outputStream) -> {
                //根据实际情况输出数据
                outputStream.write(StatusCode.SUCCESS.getMessage().getBytes());
                outputStream.flush();
                outputStream.close();
            };
        };
    }

    /**
     * 通过DirectExchange发送订单消息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/direct/order.do")
    public Callable<StreamingResponseBody> sendOrderByDirect(HttpServletRequest request, HttpServletResponse response) {
        String id = UUID.randomUUID().toString();
        String transferCode = UUID.randomUUID().toString();
        BigDecimal productPrice = new BigDecimal(80);
        Order order = new Order(id,"羽绒服",productPrice,transferCode,1.0,StatusCode.UNPAID.getValue(),new Date());
        String json = JSONObject.toJSONString(order);
        return () -> {
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            //messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
            Message message = new Message(json.getBytes(),messageProperties);
            amqpProducer.send(Constants.DIRECT_EXCHANGE,Constants.ROUTING_KEY_ORDER_DIRECT,message);
            amqpProducer.convertAndSend(Constants.DIRECT_EXCHANGE,Constants.ROUTING_KEY_ORDER_DIRECT,json);
            amqpProducer.convertSendAndReceive(Constants.DIRECT_EXCHANGE,Constants.ROUTING_KEY_ORDER_DIRECT,json);
            return (outputStream) -> {
                //根据实际情况输出数据
                outputStream.write(StatusCode.SUCCESS.getMessage().getBytes());
                outputStream.flush();
                outputStream.close();
            };
        };
    }

}
