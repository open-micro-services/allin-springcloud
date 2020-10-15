package com.boonya.springboot.redis.sbredis.util;

import java.util.concurrent.ConcurrentHashMap;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class RedisNodeManger {

    private static boolean init = false;

    private static boolean multiRedisNode = false;

    private static ConcurrentHashMap<String, JedisPool> redisNodes = new ConcurrentHashMap<String, JedisPool>();

    static {
        init();
    }

    public static boolean isMultiRedisNode() {
        return multiRedisNode;
    }

    public static void init() {
        if (!init) {
            String connections = PropertiesUtil.getConfig("redisConnections");
            if (!Utils.IsNullOrEmpty(connections)) {
                String nodes[] = connections.split(",");
                for (String node : nodes) {
                    parseNode(node);
                }
                if (redisNodes.size() > 0) {
                    multiRedisNode = true;
                }
            }
            init = true;
        }
    }

    public static void appendNode(String connections) {
        if (!Utils.IsNullOrEmpty(connections)) {
            String nodes[] = connections.split(",");
            for (String node : nodes) {
                parseNode(node);
            }
            if (redisNodes.size() > 0) {
                multiRedisNode = true;
            }
        }
    }

    public static void removeNode(String dwCode) {
        if (redisNodes.containsKey(dwCode)) {
            redisNodes.remove(dwCode);
        }
    }

    private static void parseNode(String node) {
        String[] values = node.split("#");
        String dwCode = values[0];
        String host = values[1];
        String port = values[2];
        String auth = values[3];
        JedisPool pool = RedisPool.generateJedisPool(host, port, auth);
        if (!Utils.IsNullOrEmpty(dwCode)) {
            if (pool != null) redisNodes.put(dwCode, pool);
        }
    }

    public static Jedis getJedis(String dwCode) {
        if (redisNodes.containsKey(dwCode)) {
            return redisNodes.get(dwCode).getResource();
        }
        return null;
    }

    public static boolean hasRedisNode(String dwCode) {
        return redisNodes.containsKey(dwCode);
    }

}
