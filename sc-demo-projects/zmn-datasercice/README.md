# Technologies

SpringBoot+ Web + security(OAth2)+ Thymeleaf+JDBC(Mysql)+ Redis+ RabbitMQ

# Quick Statrt

## Maven build and package

`mvn clean package -DskipTests=true `

## Run application jar

default settings:

`
java -jar zmn-dataservice-0.0.1-SNAPSHOT.jar  
`

development settings:


`
java -jar zmn-dataservice-0.0.1-SNAPSHOT.jar  --spring.profiles.active=dev
`

product settings:

`
java -jar zmn-dataservice-0.0.1-SNAPSHOT.jar  --spring.profiles.active=prod
`

and you can put more additional parameters  like change the port with `--server.port=9999 `

# Features

## RabbitMQ 

Using spring amqp framework to implement producer/consumer.

## Redis publish/subscriber

Redis publisher and subscriber to implement Pub/Sub mode.

## Redis MQ producer/consumer

Redis use list to implement Queue. Pushing new message will add to the head of list from left side , getting message from the end of list from the right size, it's still obey the FIFO rule of queues.


# References

https://hub.docker.com/_/rabbitmq

https://www.cnblogs.com/zwcry/p/9719361.html

https://www.cnblogs.com/liuyp-ken/p/10538658.html


