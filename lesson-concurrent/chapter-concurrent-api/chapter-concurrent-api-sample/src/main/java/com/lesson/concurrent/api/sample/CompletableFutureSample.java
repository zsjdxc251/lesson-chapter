package com.lesson.concurrent.api.sample;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
/**
 *    {@link CompletableFuture}
 *    {@link CompletionStage}
 *    {@link CompletionException}
 *
 *    该Api 为 jdk 1.8 提供 reactive 操作
 * @author zhengshijun
 * @version created on 2018/8/28.
 */
public class CompletableFutureSample {

    public static void main(String[] args){





        CompletableFuture.supplyAsync(()->{
            List<String> list = ImmutableList.of("3333","4444");
            println("1");
            return list;
        }).thenApplyAsync(data->{
            println(data);
            return data+"1";
        }).thenAccept(CompletableFutureSample::println).thenRun(()->{
            println("多线程");
        }).whenComplete((data,error)->{
            System.out.println(data+"-"+error);
        }).join();// 主线程等待执行完
    }
    private static void println(Object message) {
        System.out.printf("[线程 : %s] %s\n",
                Thread.currentThread().getName(), // 当前线程名称
                message);
    }
}
