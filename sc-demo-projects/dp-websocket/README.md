# Technologies

SpringBoot+ Web + Freemarker+JDBC(Mysql)+ Redis+ WebSocket

# Quick Statrt

## Maven build and package

`mvn clean package -DskipTests=true `

## Run application jar

default settings:

`
java -jar dp-websocket-0.0.1-SNAPSHOT.jar  
`

development settings:


`
java -jar dp-websocket-0.0.1-SNAPSHOT.jar  --spring.profiles.active=dev
`

product settings:

`
java -jar dp-websocket-0.0.1-SNAPSHOT.jar  --spring.profiles.active=prod
`

# Features

## WebSocket communication

Using spring WebSocket to push message or listener commands from pages.

## Redis publish/subscriber

Redis publisher and subscriber to implement Pub/Sub mode.

## Redis MQ producer/consumer

Redis use list to implement Queue.push message add to list left and poll message from right,so it's still obey the FIFO rules of queue.


# References

https://www.cnblogs.com/zwcry/p/9719361.html

https://www.cnblogs.com/liuyp-ken/p/10538658.html

