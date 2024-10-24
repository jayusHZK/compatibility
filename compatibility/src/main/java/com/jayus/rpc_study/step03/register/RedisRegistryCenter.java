package com.jayus.rpc_study.step03.register;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ClassName RedisRegistryCenter
 * @Description: Redis 模拟 RPC 注册中心
 * @date: 2024/10/22 20:52
 */
public class RedisRegistryCenter {

    private static Jedis jedis; // 非切片客户端连接

    public static void init(String host,int port) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setTestOnBorrow(false);
        JedisPool jedisPool = new JedisPool(config, host, port);
        jedis = jedisPool.getResource();
    }

    /*
    注册生产者
     */
    public static Long registryProvider(String nozzle,String alias,String info) {
        return jedis.sadd(nozzle+"_" + alias,info);
    }

    /*
    获取生产者
     */
    public static String obtainProvider(String nozzle,String alias) {
        return jedis.srandmember(nozzle + "_" + alias);
    }

    public static Jedis jedis() {
        return jedis;
    }

}
