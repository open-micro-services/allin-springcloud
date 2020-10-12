# RabbitMQ

http://www.rabbitmq.com/

https://github.com/rabbitmq

# RabbitMQ Environment support

Download and install Erlang: http://www.erlang.org/

# RabbitMQ Introduce

MQ全称为Message Queue, 消息队列（MQ）是一种应用程序对应用程序的通信方法。

应用程序通过读写出入队列的消息（针对应用程序的数据）来通信，而无需专用连接来链接它们。

消息传递指的是程序之间通过在消息中发送数据进行通信，而不是通过直接调用彼此来通信，直接调用通常是用于诸如远程过程调用的技术。

排队指的是应用程序通过 队列来通信。队列的使用除去了接收和发送应用程序同时执行的要求。其中较为成熟的MQ产品有IBM WEBSPHERE MQ等等。

# RabbitMQ Features

MQ是消费-生产者模型的一个典型的代表，一端往消息队列中不断写入消息，而另一端则可以读取或者订阅队列中的消息。MQ和JMS类似，但不同的是JMS是SUN JAVA消息中间件服务的一个标准和API定义，而MQ则是遵循了AMQP协议的具体实现和产品。

# RabbitMQ Using situation

在项目中，将一些无需即时返回且耗时的操作提取出来，进行了异步处理，而这种异步处理的方式大大的节省了服务器的请求响应时间，从而提高了系统的吞吐量。

# RabbitMQ admin management page

http://localhost:15672


# Reference

https://www.cnblogs.com/ericli-ericli/p/5902270.html

https://github.com/hmilyos/rabbitmqdemo
