package com.lesson.concurrent.api.sample;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 *   {@link ThreadPoolExecutor}
 *   {@link ThreadFactory}
 *   {@link RejectedExecutionException}
 *   {@link RejectedExecutionHandler}
 *   {@link Future}
 *
 *   {@link TimeUnit}
 *   {@link ArrayBlockingQueue}
 *   {@link Executors}
 *   {@link Executors.DefaultThreadFactory}
 *   {@link ExecutionException}
 *
 * @see InterruptedException
 * @author zhengshijun
 * @version created on 2018/8/28.
 */
public class ThreadPoolExecutorSample {

    public static void main(String[] args){
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(2,20,300,
                        TimeUnit.SECONDS,new ArrayBlockingQueue<>(200),threadFactory,(r,e)->{

                });

        Future<String> future = threadPoolExecutor.submit(()->{

            return "zsjdxc251";
        });

        String result = null;
        try {
            result = future.get();
        } catch (InterruptedException|ExecutionException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();

    }
}
