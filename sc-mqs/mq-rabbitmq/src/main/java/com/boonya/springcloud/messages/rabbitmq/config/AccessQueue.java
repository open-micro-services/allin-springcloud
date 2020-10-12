package com.boonya.springcloud.messages.rabbitmq.config;

import com.boonya.springcloud.messages.rabbitmq.util.Constants;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Copyright: 2019-2021
 * @FileName: AccessQueue.java
 * @Author: PJL
 * @Date: 2020/4/21 18:54
 * @Description: 消息队列实例
 */
@Configuration
public class AccessQueue {

    /**
     * RabbitMQ 相关知识说明：
     *
     * Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
     * Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
     * Queue:消息的载体,每个消息都会被投到一个或多个队列。
     * Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
     * Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
     * vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
     * Producer:消息生产者,就是投递消息的程序.
     * Consumer:消息消费者,就是接受消息的程序.
     * Channel:消息通道,在客户端的每个连接里,可建立多个channel.
     */

    /**
     * BATCH队列实例
     *
     * @return
     */
    @Bean
    public Queue queueForBatch() {
        return new Queue(Constants.QUEUE_BATCH, true); // true表示持久化该队列
    }

    /**
     * DEBUG队列实例
     *
     * @return
     */
    @Bean
    public Queue queueForDebug() {
        return new Queue(Constants.QUEUE_DEBUG, true); // true表示持久化该队列
    }

    /**
     * ERROR队列
     *
     * @return
     */
    @Bean
    public Queue queueForError() {
        return new Queue(Constants.QUEUE_ERROR, true); // true表示持久化该队列
    }

    /**
     * INFO队列
     *
     * @return
     */
    @Bean
    public Queue queueForInfo() {
        return new Queue(Constants.QUEUE_INFO, true); // true表示持久化该队列
    }

    /**
     * DELETE队列
     *
     * @return
     */
    @Bean
    public Queue queueForDelete() {
        return new Queue(Constants.QUEUE_DELETE, true); // true表示持久化该队列
    }

    /**
     * 订单队列实例
     *
     * @return
     */
    @Bean
    public Queue queueForOrder() {
        return new Queue(Constants.QUEUE_ORDER, true); // true表示持久化该队列
    }

    /**
     * 支付队列
     *
     * @return
     */
    @Bean
    public Queue queueForPay() {
        return new Queue(Constants.QUEUE_PAY, true); // true表示持久化该队列
    }

    /**
     * 邮件队列
     *
     * @return
     */
    @Bean
    public Queue queueForEmail() {
        return new Queue(Constants.QUEUE_EMAIL, true); // true表示持久化该队列
    }

    /**
     * 短信队列
     *
     * @return
     */
    @Bean
    public Queue queueForSms() {
        return new Queue(Constants.QUEUE_SMS, true); // true表示持久化该队列
    }

    /**
     * 位置队列
     *
     * @return
     */
    @Bean
    public Queue queueForPosition() {
        return new Queue(Constants.QUEUE_POSITION, true); // true表示持久化该队列
    }
}
