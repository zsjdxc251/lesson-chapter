package com.lesson.concurrent.api.sample;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.RunnableScheduledFuture;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 *
 *    调度线程池
 *
 *   {@link ScheduledThreadPoolExecutor}
 *   {@link ScheduledFuture}
 *   {@link RunnableScheduledFuture}
 *   {@link RunnableFuture}
 *   {@code ScheduledFuture} -> {@code RunnableScheduledFuture} -> {@code RunnableFuture}
 *
 *   {@link RejectedExecutionException}
 *   {@link RejectedExecutionHandler}
 *   {@link Executors.DefaultThreadFactory}
 *   {@link TimeUnit}
 * @author zhengshijun
 * @version created on 2018/8/29.
 */
public class ScheduledThreadPoolExecutorSample {

    public static void main(String[] args){
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(20,threadFactory,(r,e)->{

        });

        ScheduledFuture scheduled = scheduledThreadPoolExecutor.schedule(()->{

            System.out.println("1");
        },1, TimeUnit.SECONDS);

        ScheduledFuture scheduleAtFixedRate = scheduledThreadPoolExecutor.scheduleAtFixedRate(()->{

            System.out.println(2);
        },1,2,TimeUnit.SECONDS);



        Executors.newCachedThreadPool();

        Executors.newFixedThreadPool(1);


    }
}



