package com.lesson.concurrent.api.sample;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 *
 *    {@link Executors}
 *    {@link ExecutorService} extends {@link Executor}
 *    {@link ScheduledExecutorService}
 *
 *
 * @author zhengshijun
 * @version created on 2018/8/29.
 */
public class ExecutorsSample {

    public static void main(String[] args){


        ExecutorService executorService = Executors.newCachedThreadPool();


        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(20);

    }
}
