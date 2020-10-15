package com.zmn.xht.dataservice.zmndataservice.redis.mq;

import com.zmn.xht.dataservice.zmndataservice.redis.RedisChannel;
import com.zmn.xht.dataservice.zmndataservice.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MQ消费线程管理对象
 */
public class MQConsumer {

    private final static Logger logger = LoggerFactory.getLogger(MQConsumer.class);

    /**
     * 启动消费线程
     */
    public  void start(){
        logger.info("Redis队列消费启动.....");
        Thread thread=new Thread(new ConsumeThread());
        thread.start();
        logger.info("Redis队列消费启动.....完成!");

        logger.info("Redis队列(指定通道)消费启动.....");
        Thread thread2=new Thread(new ConsumeByChannelThread(RedisChannel.TEST_CHANNEL.getValue()));
        thread2.start();
        logger.info("Redis队列(指定通道)消费启动.....完成!");
    }

    /**
     * 消费线程
     */
    public class ConsumeThread implements Runnable {

        @Override
        public void run() {
            while (true){
                // 线程执行消费逻辑
                if(RedisService.getRedisMQ()!=null&& RedisService.getRedisMQ().size("")>0){
                    this.consume();
                }else{
                    try{
                        Thread.sleep(30);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        }

        /**
         * 消息消费处理
         */
        private void consume(){
            String message=RedisService.getRedisMQ().rightPop("");
            logger.info("Redis队列消费....."+message);
        }
    }

    /**
     * 消费线程
     */
    public class ConsumeByChannelThread implements Runnable {

        String channel="";

        public ConsumeByChannelThread(String channel){
            this.channel=channel;
        }

        @Override
        public void run() {
            while (true){
                // 线程执行消费逻辑
                if(RedisService.getRedisMQ()!=null&&RedisService.getRedisMQ().size(channel)>0){
                    this.consume();
                }else{
                    try{
                        Thread.sleep(30);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        }

        /**
         * 消息消费处理
         */
        private void consume(){
            String message=RedisService.getRedisMQ().rightPop(channel);
            logger.info("Redis队列(指定通道)消费....."+message);
        }
    }
}
