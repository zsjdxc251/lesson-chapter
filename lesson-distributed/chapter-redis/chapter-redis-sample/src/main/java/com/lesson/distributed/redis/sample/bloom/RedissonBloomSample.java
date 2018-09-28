package com.lesson.distributed.redis.sample.bloom;

import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author zhengshijun
 * @version created on 2018/9/28.
 */
public class RedissonBloomSample {

    public static void main(String[] args){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://121.196.232.248:7777").setPassword("12345QWERTYUIO");
        RedissonClient redissonClient= Redisson.create(config);

        RBloomFilter<String> rBloomFilter = redissonClient.getBloomFilter("demo");

        rBloomFilter.tryInit(1000000,0.01);

        Iterable<String> keys = redissonClient.getKeys().getKeys();
        keys.forEach(rBloomFilter::add);



        System.out.println(rBloomFilter.contains("demo"));

        redissonClient.shutdown();



    }
}
