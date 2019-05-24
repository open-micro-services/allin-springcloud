package com.boonya.springboot.redis.sbredis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {

	private static JedisPool pool;// jedis连接池

	private static int maxTotal = 500;// 最大连接数

	private static int maxIdle = 10;// 最大空闲连接数

	private static int minIdle = 5;// 最小空闲连接数

	private static boolean testOnBorrow = true;// 在取连接时测试连接的可用性

	private static boolean testOnReturn = false;// 再还连接时不测试连接的可用性

	private static String host = PropertiesUtil.getConfig("redisServerHost");
	private static String portStr = PropertiesUtil.getConfig("redisServerPort");
	private static String auth = PropertiesUtil.getConfig("redisServerAuth");
	private static Boolean redisServer = Boolean.parseBoolean(PropertiesUtil.getConfig("redisServer"));
	static {
		initPool();// 初始化连接池
	}

	public static Jedis getJedis() {
		if(redisServer) return pool.getResource();
		else return null;
	}

	public static void close(Jedis jedis) {
		jedis.close();
	}

	private static void initPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMinIdle(minIdle);
		config.setTestOnBorrow(testOnBorrow);
		config.setTestOnReturn(testOnReturn);
		config.setBlockWhenExhausted(true);
    	if(!Utils.IsNullOrEmpty(host) && redisServer){
    		Integer port = 6379;
    		if(!Utils.IsNullOrEmpty(portStr)) port = Integer.parseInt(portStr);
    		pool = new JedisPool(config, host, port, 5000, auth);
    		//设置token存活时间为60秒	
    		System.out.println("Redis初始化完成");
    	}else{
    		System.out.println("Redis未初始化");
    	}
	}
	
	public static JedisPool generateJedisPool(String host,String portStr,String auth){
		JedisPool pool=null;
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMinIdle(minIdle);
		config.setTestOnBorrow(testOnBorrow);
		config.setTestOnReturn(testOnReturn);
		config.setBlockWhenExhausted(true);
    	if(!Utils.IsNullOrEmpty(host)){
    		Integer port = 6379;
    		if(!Utils.IsNullOrEmpty(portStr)) port = Integer.parseInt(portStr);
    		pool = new JedisPool(config, host, port, 5000, auth);
    		//设置token存活时间为60秒	
    		System.out.println(host+":"+port+" redis节点初始化完成");
    	}else{
    		System.out.println(host+":"+portStr+" redis节点未初始化");
    	}
    	return pool;
	}
}
