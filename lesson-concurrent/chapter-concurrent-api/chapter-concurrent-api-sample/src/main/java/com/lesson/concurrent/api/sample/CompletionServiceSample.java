package com.lesson.concurrent.api.sample;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 *
 *    {@link CompletionService}
 *    {@link ExecutorCompletionService}
 *    {@link ExecutionException}
 *
 *    {@link Executors}
 *
 * @see InterruptedException
 * @author zhengshijun
 * @version created on 2018/8/28.
 */
public class CompletionServiceSample {

    public static void main(String[] args){

        CompletionService<String> completionService = new ExecutorCompletionService<>(Executors.newSingleThreadExecutor());

        completionService.submit(()->{

            return "zsjdxc251";
        });



        String result = null;
        try {
            Future<String> future = completionService.take();

            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }
}
