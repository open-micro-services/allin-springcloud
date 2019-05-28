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
public class RedisUtil {

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
    public static Boolean getJedisBoolean() {
        Jedis jedis = null;
        boolean result = false;
        try {
            jedis = RedisPool.getJedis();
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

    private static Jedis getJedisFromPool() {
        return RedisPool.getJedis();
    }

    //keys
    public static Set<String> keys(String key) {
        Jedis jedis = null;
        Set<String> result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.keys(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //get
    public static String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //set
    public static String set(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //hset
    public static Long hset(String key, String field, String value) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //hget
    public static String hget(String key, String field) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //hdel
    public static Long hdel(String key, String... fields) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.hdel(key, fields);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //hkeys
    public static Set<String> hkeys(String key) {
        Jedis jedis = null;
        Set<String> result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.hkeys(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //sadd
    public static Long sadd(String key, String... members) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.sadd(key, members);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //smembers
    public static Set<String> smembers(String key) {
        Jedis jedis = null;
        Set<String> result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.smembers(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //lpush
    public static Long lpush(String key, String... strings) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.lpush(key, strings);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //lrange
    public static List<String> lrange(String key, long start, long end) {
        Jedis jedis = null;
        List<String> result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.lrange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //llen
    public static Long llen(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.llen(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }


    //del
    public static Long del(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //ttl
    public static Long ttl(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.ttl(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //expire
    public static Long expire(String key, int seconds) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.expire(key, seconds);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) jedis.close();
        }
        return result;
    }

    //pexpireAt
    public static Long pexpireAt(String key, long millisecondsTimestamp) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
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
    public static Long geoadd(String key, GeoCoordinate coordinate, String memberName) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
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
    public static Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
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
    public static List<GeoRadiusResponse> georadius(String key, GeoCoordinate coordinate, double radius) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
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
    public static List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
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
    public static Double geoDist(String key, String member1, String member2, GeoUnit unit) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
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
    public static List<String> geohash(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
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
    public static List<GeoCoordinate> geopos(String key, String... members) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            return jedis.geopos(key, members);
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
        final Jedis jedis = RedisUtil.getJedisFromPool();
//		jedis.flushAll();
        Set<String> keys = RedisUtil.keys("xht:*");
        for (String key : keys) {
            System.out.println(key);
            if (key.startsWith(MOBILE_TOKEN_KEY)) {
                System.out.println(RedisUtil.get(key));
            } else if (key.startsWith(SSGJ_HLY_KEY)) {
                List<String> list = RedisUtil.lrange(key, 0, RedisUtil.llen(key));
                System.out.println(JSONArray.fromObject(list).toString());
            } else if (key.startsWith(DEPARTMENT_KEY)) {
                Set<String> sets = RedisUtil.smembers(key);
                System.out.println(JSONArray.fromObject(sets).toString());
            } else {
                Set<String> strs = RedisUtil.hkeys(key);
                for (String str : strs) {
                    System.out.println(str + "=" + RedisUtil.hget(key, str));
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
