# Apache Kafka

Kafka是由Apache软件基金会开发的一个开源流处理平台，由Scala和Java编写。

Kafka是一种高吞吐量的分布式发布订阅消息系统，它可以处理消费者规模的网站中的所有动作流数据。 

这种动作（网页浏览，搜索和其他用户的行动）是在现代网络上的许多社会功能的一个关键因素。 

这些数据通常是由于吞吐量的要求而通过处理日志和日志聚合来解决。 

对于像Hadoop的一样的日志数据和离线分析系统，但又要求实时处理的限制，这是一个可行的解决方案。

Kafka的目的是通过Hadoop的并行加载机制来统一线上和离线的消息处理，也是为了通过集群来提供实时的消息。

#  Apache Features

Kafka [1]  是一种高吞吐量 [2]  的分布式发布订阅消息系统，有如下特性：

    通过O(1)的磁盘数据结构提供消息的持久化，这种结构对于即使数以TB的消息存储也能够保持长时间的稳定性能。

    高吞吐量 [2]  ：即使是非常普通的硬件Kafka也可以支持每秒数百万 [2]  的消息。

    支持通过Kafka服务器和消费机集群来分区消息。

    支持Hadoop并行数据加载。 [3] 

Kafka通过官网发布了最新版本2.0.0

# Apache Terminology

Broker

    Kafka集群包含一个或多个服务器，这种服务器被称为broker [5] 
    
Topic

    每条发布到Kafka集群的消息都有一个类别，这个类别被称为Topic。（物理上不同Topic的消息分开存储，逻辑上一个Topic的消息虽然保存于一个或多个broker上但用户只需指定消息的Topic即可生产或消费数据而不必关心数据存于何处）
   
Partition

    Partition是物理上的概念，每个Topic包含一个或多个Partition.
    
Producer

     负责发布消息到Kafka broker
     
Consumer

    消息消费者，向Kafka broker读取消息的客户端。
    
Consumer Group

    每个Consumer属于一个特定的Consumer Group（可为每个Consumer指定group name，若不指定group name则属于默认的group）。
    
# Downloads

http://kafka.apache.org/downloads

# Start Zookeeper and Kafka

linux:

    #zookeeper
    bin/zkServer.sh start
    #kafka
    bin/kafka-server-start.sh config/server.properties
    
windows:

     #zookeeper
     .\bin\zkServer
     #kafka
    .\bin\windows\kafka-server-start.bat.\config\server.properties

# Console test logs
  
    2018-12-28 10:58:57.964  INFO 25520 --- [nio-8080-exec-1] o.a.kafka.common.utils.AppInfoParser     : Kafka version : 2.0.1
    2018-12-28 10:58:57.964  INFO 25520 --- [nio-8080-exec-1] o.a.kafka.common.utils.AppInfoParser     : Kafka commitId : fa14705e51bd2ce5
    2018-12-28 10:58:57.975  INFO 25520 --- [ad | producer-1] org.apache.kafka.clients.Metadata        : Cluster ID: rwz1jPTQTBaQcGd04eNWEQ
    2018-12-28 10:58:58.011  INFO 25520 --- [ntainer#0-0-C-1] c.b.s.messages.kafka.consumer.Consumer   : ===========record:ConsumerRecord(topic = serviceTopic, partition = 0, offset = 2, CreateTime = 1545965937980, serialized key size = -1, serialized value size = 100, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = {"id":"4f885d90-b969-48b6-a98f-42acb0af599d","message":"hishdfosd","sendTime":"2018-12-28 10:58:57"})
    2018-12-28 10:58:58.011  INFO 25520 --- [ntainer#0-0-C-1] c.b.s.messages.kafka.consumer.Consumer   : ===========message:{"id":"4f885d90-b969-48b6-a98f-42acb0af599d","message":"hishdfosd","sendTime":"2018-12-28 10:58:57"}
    2018-12-28 11:10:19.750  INFO 25520 --- [nio-8080-exec-5] c.b.s.messages.kafka.consumer.Consumer   : message:{"id":"d31d801a-68c1-408b-bfa9-cfa90283cc15","message":"hishdfosd","sendTime":"2018-12-28 11:10:19"}
    2018-12-28 11:10:19.787  INFO 25520 --- [ntainer#0-0-C-1] c.b.s.messages.kafka.consumer.Consumer   : ===========record:ConsumerRecord(topic = serviceTopic, partition = 0, offset = 3, CreateTime = 1545966619754, serialized key size = -1, serialized value size = 100, headers = RecordHeaders(headers = [], isReadOnly = false), key = null, value = {"id":"d31d801a-68c1-408b-bfa9-cfa90283cc15","message":"hishdfosd","sendTime":"2018-12-28 11:10:19"})
    2018-12-28 11:10:19.787  INFO 25520 --- [ntainer#0-0-C-1] c.b.s.messages.kafka.consumer.Consumer   : ===========message:{"id":"d31d801a-68c1-408b-bfa9-cfa90283cc15","message":"hishdfosd","sendTime":"2018-12-28 11:10:19"}