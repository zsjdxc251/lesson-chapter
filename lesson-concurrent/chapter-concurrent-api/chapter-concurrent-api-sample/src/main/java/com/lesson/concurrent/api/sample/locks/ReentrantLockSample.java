package com.lesson.concurrent.api.sample.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *  {@link ReentrantLock}
 * @author zhengshijun
 * @version created on 2018/10/16.
 */
public class ReentrantLockSample {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception{

        new Thread(()->{
            lock.lock();


        }).start();



        TimeUnit.SECONDS.sleep(1);
        Thread thread = new Thread(()->{
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();


        thread.interrupt();


        System.out.println("sleep");

    }
}
