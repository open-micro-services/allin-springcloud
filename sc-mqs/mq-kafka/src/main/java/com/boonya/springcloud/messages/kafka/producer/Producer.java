package com.boonya.springcloud.messages.kafka.producer;

import com.boonya.springcloud.messages.kafka.consumer.Consumer;
import com.boonya.springcloud.messages.kafka.entity.Message;
import com.boonya.springcloud.utils.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: Producer
 * @Description: TODO(Kafka生产者)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2018-12-28 1:19
 */
@Component
public class Producer {

    private  final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(Message message){
        String msg=Tools.toJson(message);
        //Message message = new Message(UUID.randomUUID().toString(),"",new Date());
        logger.info("message:{}", msg);
        kafkaTemplate.send("serviceTopic",msg);
    }

}
