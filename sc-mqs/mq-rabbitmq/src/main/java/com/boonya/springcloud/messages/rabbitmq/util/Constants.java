package com.boonya.springcloud.messages.rabbitmq.util;

/**
 * @Copyright: 2019-2021
 * @FileName: Constants.java
 * @Author: PJL
 * @Date: 2020/4/20 14:36
 * @Description: 系统常量定义类
 */
public class Constants {

    /**
     * BATCH 队列---消息存储队列名
     */
    public static final String  QUEUE_BATCH = "batch.queue";

    /**
     * DEBUG 队列---消息存储队列名
     */
    public static final String  QUEUE_DEBUG = "debug.queue";

    /**
     * ERROR队列---消息存储队列名
     */
    public static final String  QUEUE_ERROR = "error.queue";

    /**
     * INFO队列---消息存储队列名
     */
    public static final String  QUEUE_INFO = "info.queue";

    /**
     * DELETE 队列---消息存储队列名
     */
    public static final String  QUEUE_DELETE = "delete.queue";

    /**
     * 订单队列---消息存储队列名
     */
    public static final String  QUEUE_ORDER = "order.queue";

    /**
     * 支付队列---消息存储队列名
     */
    public static final String  QUEUE_PAY = "pay.queue";

    /**
     * 邮件队列---消息存储队列名
     */
    public static final String  QUEUE_EMAIL = "email.queue";

    /**
     * 短信队列---消息存储队列名
     */
    public static final String  QUEUE_SMS = "sms.queue";

    /**
     * 位置队列---消息存储队列名
     */
    public static final String  QUEUE_POSITION = "position.queue";


    /**
     * Topic交换器---交换器负责转发消息到指定的路由队列
     */
    public static final String  TOPIC_EXCHANGE= "topicExchange";

    /**
     * Direct交换器---交换器负责转发消息到指定的路由队列
     */
    public static final String  DIRECT_EXCHANGE= "directExchange";

    /**
     * Fanout交换器---交换器负责转发消息到指定的路由队列
     */
    public static final String  FANOUT_EXCHANGE= "fanoutExchange";

    /**
     * 批量路由键---路由键是为了配合交换器将消息放入对应的队列
     */
    public static final String  ROUTING_KEY_BATCH_TOPIC = "routing.key.batch.topic";

    /**
     * 订单路由键---路由键是为了配合交换器将消息放入对应的队列
     */
    public static final String  ROUTING_KEY_ORDER_TOPIC = "routing.key.order.topic";

    /**
     * 支付路由键---路由键是为了配合交换器将消息放入对应的队列
     */
    public static final String  ROUTING_KEY_PAY_TOPIC = "routing.key.pay.topic";

    /**
     * 邮件路由键---路由键是为了配合交换器将消息放入对应的队列
     */
    public static final String  ROUTING_KEY_EMAIL_TOPIC = "routing.key.email.topic";

    /**
     * 短信路由键---路由键是为了配合交换器将消息放入对应的队列
     */
    public static final String  ROUTING_KEY_SMS_TOPIC = "routing.key.sms.topic";

    /**
     * 批量路由键---路由键是为了配合交换器将消息放入对应的队列
     */
    public static final String  ROUTING_KEY_BATCH_DIRECT = "routing.key.batch.direct";

    /**
     * 订单路由键---路由键是为了配合交换器将消息放入对应的队列
     */
    public static final String  ROUTING_KEY_ORDER_DIRECT = "routing.key.order.direct";

    /**
     * 支付路由键---路由键是为了配合交换器将消息放入对应的队列
     */
    public static final String  ROUTING_KEY_PAY_DIRECT = "routing.key.pay.direct";

    /**
     * 邮件路由键---路由键是为了配合交换器将消息放入对应的队列
     */
    public static final String  ROUTING_KEY_EMAIL_DIRECT = "routing.key.email.direct";

    /**
     * 短信路由键---路由键是为了配合交换器将消息放入对应的队列
     */
    public static final String  ROUTING_KEY_SMS_DIRECT = "routing.key.sms.direct";
}
