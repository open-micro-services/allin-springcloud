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

and you can put more adtional parameters  like change the port with `--server.port=9999 `

# Features

## WebSocket communication

Using spring WebSocket to push message or listener commands from pages.

## Redis publish/subscriber

Redis publisher and subscriber to implement Pub/Sub mode.

## Redis MQ producer/consumer

Redis use list to implement Queue. Pushing new message will add to the head of list from left side , getting message from the end of list from the right size, it's still obey the FIFO rule of queues.


# References

https://www.tutorialspoint.com/websockets/websockets_send_receive_messages.htm

https://www.cnblogs.com/zwcry/p/9719361.html

https://www.cnblogs.com/liuyp-ken/p/10538658.html


