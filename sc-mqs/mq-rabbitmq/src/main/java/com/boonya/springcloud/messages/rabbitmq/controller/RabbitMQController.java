package com.boonya.springcloud.messages.rabbitmq.controller;

import com.boonya.springcloud.messages.rabbitmq.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.concurrent.Callable;

/**
 * @ClassName: RabbitMQController
 * @Description: TODO(RabbitMQ消息发布订阅控制接口 - - 此处最好是线程异步调用 ， 否则违背了RabbitMQ的高性能吞吐设计)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 11:53
 */
@RestController
@RequestMapping("rabbitmq")
public class RabbitMQController {

    @Autowired
    Producer producer;

    @RequestMapping("/order/{msg}")
    public String sendOrderMessage(@PathVariable("msg") String msg) {
        producer.sendOrderMessage(msg);
        return "Send msg to order.queue success!";
    }

    @RequestMapping("/pay/{msg}")
    public String sendPayMessage(@PathVariable("msg") String msg) {
        producer.sendPayMessage(msg);
        return "Send msg to pay.queue success!";
    }

    /**
     * 异步回调接口
     *
     * @param msg
     * @return
     */
    @RequestMapping("/async/order/{msg}")
    public Callable<StreamingResponseBody> sendOrderMessageByAsync(@PathVariable("msg") String msg) {
        return () -> {
            //调用Service方法处理业务逻辑
            producer.sendOrderMessage(msg);
            return (outputStream) -> {
                //根据实际情况输出数据
                outputStream.write("Send msg to order.queue success!".getBytes());
                outputStream.flush();
                outputStream.close();
            };
        };
    }

    /**
     * 异步回调接口
     *
     * @param msg
     * @return
     */
    @RequestMapping("/async/pay/{msg}")
    public Callable<StreamingResponseBody> sendPayMessageByAsync(@PathVariable("msg") String msg) {

        return () -> {
            //调用Service方法处理业务逻辑
            producer.sendPayMessage(msg);
            return (outputStream) -> {
                //根据实际情况输出数据
                outputStream.write("Send msg to pay.queue success!".getBytes());
                outputStream.flush();
                outputStream.close();
            };
        };
    }
}
