package com.dataservice.websocket.dpwebsocket.redis.mq;

import com.dataservice.websocket.dpwebsocket.redis.RedisService;
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
    }

    /**
     * 消费线程
     */
    public class ConsumeThread implements Runnable {

        @Override
        public void run() {
            while (true){
                // 线程执行消费逻辑
                if(RedisService.getRedisMQ()!=null&&RedisService.getRedisMQ().size("mq")>0){
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
            String message=RedisService.getRedisMQ().rightPop("mq");
            logger.info("Redis队列消费....."+message);
        }
    }
}
