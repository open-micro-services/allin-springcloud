package com.boonya.springcloud.messages.rabbitmq.controller;

import com.alibaba.fastjson.JSONObject;
import com.boonya.springcloud.messages.rabbitmq.Bean.Pay;
import com.boonya.springcloud.messages.rabbitmq.producer.RabbitProducer;
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
import java.util.UUID;
import java.util.concurrent.Callable;
import static org.mockito.Mockito.mock;

/**
 * @ClassName: RabbitPublishController
 * @Description: TODO(RabbitMQ消息发布订阅控制接口 - - 接口异步调用 ， 否则违背了RabbitMQ的高性能吞吐设计)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 11:53
 */
@RestController
@RequestMapping("/rabbitmq/rabbit")
public class PayPublishController {

    @Autowired
    RabbitProducer rabbitProducer;

    /**
     * 通过TopicExchange发送支付消息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/topic/pay.do")
    public Callable<StreamingResponseBody> sendPayByTopic(HttpServletRequest request, HttpServletResponse response) {
        Pay pay = mock(Pay.class);
        pay.setId(UUID.randomUUID().toString());
        String json = JSONObject.toJSONString(pay);
        return () -> {
            MessageProperties messageProperties = new MessageProperties();
            //messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
            Message message = new Message(json.getBytes(),messageProperties);
            rabbitProducer.send(Constants.TOPIC_EXCHANGE,Constants.ROUTING_KEY_ORDER_TOPIC,message);
            rabbitProducer.convertAndSend(Constants.TOPIC_EXCHANGE,Constants.ROUTING_KEY_ORDER_TOPIC,json);
            rabbitProducer.convertSendAndReceive(Constants.TOPIC_EXCHANGE,Constants.ROUTING_KEY_ORDER_TOPIC,json);
            return (outputStream) -> {
                //根据实际情况输出数据
                outputStream.write(StatusCode.SUCCESS.getMessage().getBytes());
                outputStream.flush();
                outputStream.close();
            };
        };
    }

    /**
     * 通过DirectExchange发送支付消息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/direct/pay.do")
    public Callable<StreamingResponseBody> sendPayByDirect(HttpServletRequest request, HttpServletResponse response) {
        Pay pay = mock(Pay.class);
        pay.setId(UUID.randomUUID().toString());
        String json = JSONObject.toJSONString(pay);
        return () -> {
            MessageProperties messageProperties = new MessageProperties();
            //messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
            messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
            Message message = new Message(json.getBytes(),messageProperties);
            rabbitProducer.send(Constants.DIRECT_EXCHANGE,Constants.ROUTING_KEY_ORDER_DIRECT,message);
            rabbitProducer.convertAndSend(Constants.DIRECT_EXCHANGE,Constants.ROUTING_KEY_ORDER_DIRECT,json);
            rabbitProducer.convertSendAndReceive(Constants.DIRECT_EXCHANGE,Constants.ROUTING_KEY_ORDER_DIRECT,json);
            return (outputStream) -> {
                //根据实际情况输出数据
                outputStream.write(StatusCode.SUCCESS.getMessage().getBytes());
                outputStream.flush();
                outputStream.close();
            };
        };
    }

}
