package com.lesson.concurrent.api.sample;


import com.alibaba.fastjson.JSON;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 *
 *   {@link CyclicBarrier}
 *   {@link BrokenBarrierException}
 *   {@link CountDownLatch}
 *   {@link Exchanger}
 *   {@link Semaphore}
 *   {@link Phaser}
 *
 *   {@link ThreadPoolExecutor}
 *   {@link ThreadFactory}
 *   {@link ArrayBlockingQueue}
 *
 *
 * @see Runtime
 * @author zhengshijun
 * @version created on 2018/8/28.
 */
public class CountDownLatchSample {

    private static  CountDownLatch countDownLatch = new CountDownLatch(1);
    private static  ThreadFactory threadFactory = Executors.defaultThreadFactory();
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),10,60,TimeUnit.SECONDS,new ArrayBlockingQueue<>(50),threadFactory);

    public static void main(String[] args){


    }
}
