package com.lesson.distributed.redis.sample.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zhengshijun
 * @version created on 2018/9/28.
 */
public class RedissonLockSample {

    public void execute(){

        System.out.println("execute");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://121.196.232.248:7777").setPassword("12345QWERTYUIO");
        RedissonClient redissonClient= Redisson.create(config);

        RLock lock = redissonClient.getLock("RedissonLockSample");
        RedissonLockSample redissonLockSample = new RedissonLockSample();
        int count = 20;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        IntStream.range(0,20).forEach(x->{
            new Thread(()->{
                lock.lock();
                redissonLockSample.execute();
                lock.unlock();
                countDownLatch.countDown();
            }).start();
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redissonClient.shutdown();

    }
}
