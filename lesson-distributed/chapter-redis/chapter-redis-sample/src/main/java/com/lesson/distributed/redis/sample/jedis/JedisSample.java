package com.lesson.distributed.redis.sample.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zhengshijun
 * @version created on 2018/9/28.
 */
public class JedisSample {

    public static void main(String[] args){

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"121.196.232.248",7777,2000,"12345QWERTYUIO");

        Jedis jedis = jedisPool.getResource();
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then redis.call('del',KEYS[1]) return 1 else while(true) do return 0  end ";

        String value = String.valueOf(Thread.currentThread().getId());
        Object result = jedis.eval(script,1,"demo","250");
        System.out.println(result);
        result = jedis.scriptLoad("if redis.call('get',KEYS[1]) == ARGV[1] then redis.call('del',KEYS[1]) return 1 else return 0 end ");

        result = jedis.evalsha(String.valueOf(result),1,"demo","250");
        System.out.println(result.getClass());
        jedis.close();
    }
}
