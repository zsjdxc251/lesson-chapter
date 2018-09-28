package com.lesson.distributed.redis.sample.sentinel;

import com.google.common.collect.ImmutableSet;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @author zhengshijun
 * @version created on 2018/9/27.
 */
public class SentinelSample {


    public static void main(String[] args){


//        HostAndPort hostAndPort = new HostAndPort();
//
//        // 哨兵集群地址


       JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("", ImmutableSet.of("127.0.0.1:7777"),"12345");

    }
}
