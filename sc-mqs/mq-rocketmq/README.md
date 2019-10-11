# Technologies

SpringBoot+ Web + Freemarker+JDBC(Mysql)+ Redis+ RocketMQ

# Quick Start

## Maven build and package

`mvn clean package -DskipTests=true `

## Run application jar

default settings:

`
java -jar mq-rocketmq-0.0.1-SNAPSHOT.jar  
`

development settings:


`
java -jar mq-rocketmq-0.0.1-SNAPSHOT.jar  --spring.profiles.active=dev
`

product settings:

`
java -jar mq-rocketmq-0.0.1-SNAPSHOT.jar  --spring.profiles.active=prod
`

and you can put more additional parameters  like change the port with `--server.port=9999 `

# Features

## RocketMQ producer

RocketMQ producer publish a message to topic with tag from group.

## RocketMQ consumer

RocketMQ consumer subscribe topic and tag from a same group.


# References

http://rocketmq.apache.org/

https://blog.csdn.net/soda_lw/article/details/86543636

https://www.cnblogs.com/wadmwz/p/10689972.html

https://www.cnblogs.com/eyesfree/p/9162505.html

https://blog.csdn.net/lvwenzhuye/article/details/81483479

https://blog.csdn.net/zhaoyan_personal/article/details/81207857

[start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true ]

error to solve problem in runbroker.cmd:   set "JAVA_OPT=%JAVA_OPT% -cp "%CLASSPATH%"" ]

# Start RocketMQ on Windows

Getting into bin dir.

## Start mqnamesrv
`
start mqnamesrv.cmd
`
##  Start mqbroker
`
start mqbroker.cmd -n 127.0.0.1:9876 autoCreateTopicEnable=true
`

## Query RocketMQ server's Settings

`
mqbroker -n 127.0.0.1:9876 -p
`
# Create topic command on Windows

## Using with -b in single node

`
mqadmin.cmd updateTopic -n 127.0.0.1:9876 -b 172.26.247.129:10911 -t mytopic
`

`
mqadmin.cmd updateTopic -n 127.0.0.1:9876 -b 172.26.247.129:10911 -t advance
`

`
mqadmin.cmd updateTopic -n 127.0.0.1:9876 -b 172.26.247.129:10911 -t animal
`

[172.26.247.129:10911 is start with mqbroker.cmd ]


# Restart load topic Logs

 C:\Users\Administrator\logs\rocketmqlogs\broker.log
 
`
2019-10-11 23:27:09 INFO main - load exist local topic, TopicConfig [topicName=NRKHDVS7OOHZUNH, readQueueNums=1, writeQueueNums=1, perm=RWX, topicFilterType=SINGLE_TAG, topicSysFlag=0, order=false]
`

`
2019-10-11 23:27:09 INFO main - load exist local topic, TopicConfig [topicName=RMQ_SYS_TRANS_HALF_TOPIC, readQueueNums=1, writeQueueNums=1, perm=RW-, topicFilterType=SINGLE_TAG, topicSysFlag=0, order=false]
`

`
2019-10-11 23:27:09 INFO main - load exist local topic, TopicConfig [topicName=BenchmarkTest, readQueueNums=1024, writeQueueNums=1024, perm=RW-, topicFilterType=SINGLE_TAG, topicSysFlag=0, order=false]
`

`
2019-10-11 23:27:09 INFO main - load exist local topic, TopicConfig [topicName=OFFSET_MOVED_EVENT, readQueueNums=1, writeQueueNums=1, perm=RW-, topicFilterType=SINGLE_TAG, topicSysFlag=0, order=false]
`

`
2019-10-11 23:27:09 INFO main - load exist local topic, TopicConfig [topicName=animal, readQueueNums=8, writeQueueNums=8, perm=RW-, topicFilterType=SINGLE_TAG, topicSysFlag=0, order=false]
`

`
2019-10-11 23:27:09 INFO main - load exist local topic, TopicConfig [topicName=mytopic, readQueueNums=8, writeQueueNums=8, perm=RW-, topicFilterType=SINGLE_TAG, topicSysFlag=0, order=false]
`

`
2019-10-11 23:27:09 INFO main - load exist local topic, TopicConfig [topicName=TBW102, readQueueNums=8, writeQueueNums=8, perm=RWX, topicFilterType=SINGLE_TAG, topicSysFlag=0, order=false]
`

`
2019-10-11 23:27:09 INFO main - load exist local topic, TopicConfig [topicName=SELF_TEST_TOPIC, readQueueNums=1, writeQueueNums=1, perm=RW-, topicFilterType=SINGLE_TAG, topicSysFlag=0, order=false]
`

`
2019-10-11 23:27:09 INFO main - load exist local topic, TopicConfig [topicName=DefaultCluster, readQueueNums=16, writeQueueNums=16, perm=RWX, topicFilterType=SINGLE_TAG, topicSysFlag=0, order=false]
`

`
2019-10-11 23:27:09 INFO main - load exist local topic, TopicConfig [topicName=advance, readQueueNums=8, writeQueueNums=8, perm=RW-, topicFilterType=SINGLE_TAG, topicSysFlag=0, order=false]
`

