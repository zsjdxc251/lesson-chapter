package com.lesson.concurrent.api.sample;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 *
 *  {@link Callable}
 *  {@link FutureTask}
 *  {@link ExecutionException}
 *
 *  {@code java.util.concurrent.FutureTask} -> {@link RunnableFuture} -> {@link Runnable}, {@link Future}
 *
 * @see Thread
 * @see Runnable
 *
 * @author zhengshijun
 * @version created on 2018/8/28.
 */
public class FutureTaskSample {

    public static void main(String[] args){

        FutureTask<String> futureTask = new FutureTask<>(()->{
            return "12345";
        });

        Thread thread = new Thread(futureTask);
        thread.start();

        String result = null;
        try {
            result = futureTask.get();
        } catch (InterruptedException|ExecutionException e) {
             e.printStackTrace();
        }
        System.out.println(result);

    }
}
