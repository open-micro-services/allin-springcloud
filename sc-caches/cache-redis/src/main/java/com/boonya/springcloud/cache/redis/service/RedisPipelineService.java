package com.boonya.springcloud.cache.redis.service;

import com.boonya.springcloud.cache.redis.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.List;

/**
 * @ClassName: RedisPipelineService
 * @Description: TODO(功能说明:Redis Pipeline 高性能处理命令操作【每个回调少于1000个指令效率最佳】)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/21 0:27
 */
public class RedisPipelineService {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * SET执行插入命令
     *
     * @param list
     */
    public  List<Object>  setAdd(List<KVParam> list){
        return redisTemplate.executePipelined(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                for (KVParam kvParam : list){
                    if(kvParam.getTimeout()>-1){
                        redisConnection.setEx(kvParam.getKey().getBytes(),kvParam.getTimeout(),kvParam.getValue().getBytes());
                    }else{
                        redisConnection.set(kvParam.getKey().getBytes(),kvParam.getValue().getBytes());
                    }
                }
                return null;
            }
        });
    }

    /**
     * HASH执行插入命令
     *
     * @param list
     */
    public  List<Object>  hashAdd(List<HashParam> list){
        return redisTemplate.executePipelined(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                for (HashParam hashParam : list){
                    redisConnection.hSet(hashParam.getKey().getBytes(),hashParam.getIdentify().getBytes(),hashParam.getValue().getBytes());
                }
                return null;
            }
        });
    }

    /**
     * GEOHASH执行插入命令
     *
     * @param list
     */
    public  List<Object>  geohashAdd(List<GeoHashParam> list){
        return redisTemplate.executePipelined(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                for (GeoHashParam geoHashParam : list){
                    redisConnection.geoAdd(geoHashParam.getKey().getBytes(),geoHashParam.getPoint(),geoHashParam.getIdentify().getBytes());
                }
                return null;
            }
        });
    }

    /**
     * ZSET执行插入命令
     *
     * @param list
     */
    public  List<Object>  zsetAdd(List<ZsetParam> list){
        return redisTemplate.executePipelined(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                for (ZsetParam zsetParam : list){
                    redisConnection.zAdd(zsetParam.getKey().getBytes(),zsetParam.getScore(),zsetParam.getIdentify().getBytes());
                }
                return null;
            }
        });
    }

    /**
     * LIST执行插入命令
     *
     * @param list
     */
    public List<Object>  listAdd(List<ListParam> list){
        return redisTemplate.executePipelined(new RedisCallback<Object>() {

            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                for (ListParam listParam:list){
                    if(listParam.isLeft()){
                        redisConnection.lPush(listParam.getKey().getBytes(),listParam.getValue().getBytes());
                        if(listParam.getRange()>-1){
                            redisConnection.lRange(listParam.getKey().getBytes(),0,listParam.getRange());
                        }
                    }else{
                        redisConnection.rPush(listParam.getKey().getBytes(),listParam.getValue().getBytes());
                       /* if(listParam.getRange()>-1){
                            // 此处必须大于限制个数的点方能取暂不实现
                            int total = listParam.getKey().length();//实际的点数
                            if(total>listParam.getRange()){
                                long start=-listParam.getRange();
                                redisConnection.lRange(listParam.getKey().getBytes(),start,total);
                            }
                        }*/
                    }

                }

                return null;
            }
        });
    }
}
