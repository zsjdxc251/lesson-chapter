package com.lesson.concurrent.api.sample;


import java.util.concurrent.*;

/**
 *
 *   {@link CyclicBarrier}
 *   {@link BrokenBarrierException}
 *   {@link CountDownLatch}
 *   {@link Exchanger}
 *   {@link Semaphore}
 *   {@link Phaser}
 *
 *
 *
 * @author zhengshijun
 * @version created on 2018/8/28.
 */
public class CountDownLatchSample {

    static  CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args){





        System.out.println("1");



        new Thread(()->{

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"countDown线程").start();


        try {

            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("休眠完成");
        countDownLatch.countDown();
        System.out.println(countDownLatch.getCount());


    }
}
