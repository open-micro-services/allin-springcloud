package com.boonya.springboot.redis.sbredis.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.geo.GeoRadiusParam;

/**
 * Jedis控制类
 *
 * @author LQC
 */
public class RedisNodeManagerUtil {

    /**
     * @see
     */
    public final static String CACHE_HLY_INFO_KEY = "xht:cache:hly:info";
    /**
     * @see
     */
    public final static String CACHE_HLY_MESSAGE_KEY = "xht:cache:hly:message";
    /**
     * @see
     */
    public final static String CACHE_HLY_APPLY_KEY = "xht:cache:hly:apply";
    /**
     * @see
     */
    public final static String CACHE_DW_ADDRESS_KEY = "xht:cache:dw:address";
    /**
     * @see
     */
    public final static String CACHE_DW_BUSINESS_KEY = "xht:cache:dw:business";
    /**
     * @see {@link }
     */
    public final static String MOBILE_TOKEN_KEY = "xht:mobile:token:";
    /**
     * @see {@link }
     */
    public final static String MOBILE_USER_KEY = "xht:mobile:user";
    /**
     * @see {@link }
     */
    public final static String HLY_POSTION_KEY = "xht:hly:postion";
    /**
     * @see {@link }
     */
    public final static String DEPARTMENT_KEY = "xht:department:";
    /**
     * @see {@link }
     */
    public final static String SSGJ_HLY_KEY = "xht:ssgj:";
    public final static Integer MOBILE_TOKEN_KEY_PERIOD_DAY = 60 * 60;

    /**
     * 判断是否连接Jedis
     */
    public static Boolean getJedisBoolean(String node) {
        Jedis jedis = null;
        boolean result = false;
        try {
            jedis = RedisNodeManger.getJedis(node);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                result = true;
                jedis.close();
            }
        }
        return result;
    }

    private static Jedis getJedisFromPool(String node) {
        return RedisNodeManger.getJedis(node);
    }

    //keys
    public static Set<String> keys(String node, String key) {
        Jedis jedis = null;
        Set<String> result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.keys(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //get
    public static String get(String node, String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //set
    public static String set(String node, String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //hset
    public static Long hset(String node, String key, String field, String value) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //hget
    public static String hget(String node, String key, String field) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //hdel
    public static Long hdel(String node, String key, String... fields) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.hdel(key, fields);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //hkeys
    public static Set<String> hkeys(String node, String key) {
        Jedis jedis = null;
        Set<String> result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.hkeys(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //sadd
    public static Long sadd(String node, String key, String... members) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.sadd(key, members);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //smembers
    public static Set<String> smembers(String node, String key) {
        Jedis jedis = null;
        Set<String> result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    /**
     * 在List头部插入元素
     *
     * @param node
     * @param key
     * @param strings
     * @return
     */
    public static Long lpush(String node, String key, String... strings) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.lpush(key, strings);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    /**
     * 在List尾部插入元素
     *
     * @param node
     * @param key
     * @param strings
     * @return
     */
    public static Long rpush(String node, String key, String... strings) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.rpush(key, strings);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //lrange
    public static List<String> lrange(String node, String key, long start, long end) {
        Jedis jedis = null;
        List<String> result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.lrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //llen
    public static Long llen(String node, String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.llen(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }


    //del
    public static Long del(String node, String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //ttl
    public static Long ttl(String node, String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.ttl(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //expire
    public static Long expire(String node, String key, int seconds) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //pexpireAt
    public static Long pexpireAt(String node, String key, long millisecondsTimestamp) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            result = jedis.pexpireAt(key, millisecondsTimestamp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    /**
     * 增加地理位置的坐标
     *
     * @param key
     * @param coordinate
     * @param memberName
     * @return
     */
    public static Long geoadd(String node, String key, GeoCoordinate coordinate, String memberName) {
        Jedis jedis = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            return jedis.geoadd(key, coordinate.getLongitude(), coordinate.getLatitude(), memberName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 批量添加地理位置
     *
     * @param key
     * @param memberCoordinateMap
     * @return
     */
    public static Long geoadd(String node, String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Jedis jedis = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            return jedis.geoadd(key, memberCoordinateMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 根据给定地理位置坐标获取指定范围内的地理位置集合（返回匹配位置的经纬度 + 匹配位置与给定地理位置的距离 + 从近到远排序，）
     *
     * @param key
     * @param coordinate
     * @param radius
     * @return List<GeoRadiusResponse>
     */
    public static List<GeoRadiusResponse> georadius(String node, String key, GeoCoordinate coordinate, double radius) {
        Jedis jedis = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            return jedis.georadius(key, coordinate.getLongitude(), coordinate.getLatitude(), radius, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 根据给定地理位置获取指定范围内的地理位置集合（返回匹配位置的经纬度 + 匹配位置与给定地理位置的距离 + 从近到远排序，）
     *
     * @param key
     * @param member
     * @param radius
     * @return List<GeoRadiusResponse>
     */
    public static List<GeoRadiusResponse> georadiusByMember(String node, String key, String member, double radius) {
        Jedis jedis = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            return jedis.georadiusByMember(key, member, radius, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }


    /**
     * 查询两位置距离
     *
     * @param key
     * @param member1
     * @param member2
     * @param unit
     * @return
     */
    public static Double geoDist(String node, String key, String member1, String member2, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            return jedis.geodist(key, member1, member2, unit);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 可以获取某个地理位置的geohash值
     *
     * @param key
     * @param members
     * @return
     */
    public static List<String> geohash(String node, String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            return jedis.geohash(key, members);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 获取地理位置的坐标
     *
     * @param key
     * @param members
     * @return
     */
    public static List<GeoCoordinate> geopos(String node, String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            return jedis.geopos(key, members);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /**
     * 发布主题方法
     *
     * @param channel
     * @param message
     * @return
     */
    public static Long publish(String node, String channel, String message) {
        Jedis jedis = null;
        try {
            jedis = RedisNodeManger.getJedis(node);
            return jedis.publish(channel,message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        String node = "";
        final Jedis jedis = RedisNodeManagerUtil.getJedisFromPool(node);
//		jedis.flushAll();
        Set<String> keys = RedisNodeManagerUtil.keys(node, "xht:*");
        for (String key : keys) {
            System.out.println(key);
            if (key.startsWith(MOBILE_TOKEN_KEY)) {
                System.out.println(RedisNodeManagerUtil.get(node, key));
            } else if (key.startsWith(SSGJ_HLY_KEY)) {
                List<String> list = RedisNodeManagerUtil.lrange(node, key, 0, RedisNodeManagerUtil.llen(node, key));
                System.out.println(JSONArray.fromObject(list).toString());
            } else if (key.startsWith(DEPARTMENT_KEY)) {
                Set<String> sets = RedisNodeManagerUtil.smembers(node, key);
                System.out.println(JSONArray.fromObject(sets).toString());
            } else {
                Set<String> strs = RedisNodeManagerUtil.hkeys(node, key);
                for (String str : strs) {
                    System.out.println(str + "=" + RedisNodeManagerUtil.hget(node, key, str));
                }
            }
        }


//		jedis.hset(MOBILE_TOKEN_KEY, "1", "1");
//    	jedis.expire(MOBILE_TOKEN_KEY, MOBILE_TOKEN_KEY_PERIOD_DAY);//token生命周期1个小时
//    	System.out.println(jedis.ttl(MOBILE_TOKEN_KEY));
//		Set<String> keys = jedis.keys("xht:*");
//		for (String key : keys) {
//			System.out.println(key);
//			if(key.startsWith(MOBILE_TOKEN_KEY)){
//				System.out.println(jedis.get(key));
//			}else if(key.startsWith(SSGJ_HLY_KEY)){
//				List<String> list = jedis.lrange(key, 0, jedis.llen(key));
//				System.out.println(JSONArray.fromObject(list).toString());
//			}else if(key.startsWith(DEPARTMENT_KEY)){
//				Set<String> sets = jedis.smembers(key);
//				System.out.println(JSONArray.fromObject(sets).toString());
//			}else{
//				Set<String> strs = jedis.hkeys(key);
//				for (String str : strs) {
//					System.out.println(str +"="+ jedis.hget(key,str));
//				}
//			}
//		}
    }
    /**
     * 说明
     * 关于Reids的设计
     * MobileControllerCache 五个cache使用hash存储
     *
     一、<"mobileTokenList",HashMap<String(uuid),Token>>

     存储类型：string
     key定义：xht:mobile:token:uuid //uuid为拼接后缀，拼接UUID方便设置超时
     value = tokenjson

     存储结构 ：
     hset xht:mobile:token:uuid1  tokenjson
     hset xht:mobile:token:uuid2  tokenjson
     hset xht:mobile:token:uuid3  tokenjson


     二、<"moblieUserList",HashMap<String(username),String(uuid)>>

     存储类型：hash
     key定义：xht:mobile:user
     field = username
     value = uuid

     hset xht:mobile:user zhangsan1  uuid1
     hset xht:mobile:user zhangsan2  uuid2
     hset xht:mobile:user zhangsan3  uuid3


     三、<"XHTCache",HashMap<String(deptCode),HashMap<String(hlyID),HlyPosition>>>

     部门存储类型：hash
     key定义：xht:department:deptCode //deptCode为拼接后缀
     value = hlyID

     sadd xht:department:deptCode  hly001
     sadd xht:department:deptCode  hly002
     sadd xht:department:deptCode  hly003


     护林员存储类型：hash
     key定义：xht:hly:postion
     field = hlyID
     value = HlyPositionjson

     hset xht:hly:postion hly001  HlyPositionjson1
     hset xht:hly:postion hly002  HlyPositionjson2
     hset xht:hly:postion hly003  HlyPositionjson3



     四、<"XHTCache",HashMap<String("ssgj_"+userId),List<HlyPosition>>>

     存储类型：list
     key =  xht:ssgj:userid   // userid 为拼接后缀
     value = HlyPositionjson

     lpush xht:ssgj:userid HlyPositionjson1
     lpush xht:ssgj:userid HlyPositionjson2
     */
}
