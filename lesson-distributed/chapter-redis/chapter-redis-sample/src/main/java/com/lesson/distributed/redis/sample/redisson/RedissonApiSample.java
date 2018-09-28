package com.lesson.distributed.redis.sample.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author zhengshijun
 * @version created on 2018/9/28.
 */
public class RedissonApiSample {


    public static void main(String[] args){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://121.196.232.248:7777").setPassword("12345QWERTYUIO");
        RedissonClient redissonClient= Redisson.create(config);










        redissonClient.shutdown();




    }
}
