package com.chapter.microservice.reactive;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.CompletionService;

import com.google.common.collect.ImmutableList;
import org.springframework.util.concurrent.ListenableFuture;

/**
 *  {@link ListenableFuture}
 *  {@link CompletionService}
 */
public class CompletableFutureSample {


    public static void main(String[] args){

        //System.out.println(ForkJoinPool.commonPool().getParallelism());



        CompletableFuture.supplyAsync(()->{
            List<String> list = ImmutableList.of("3333");
            println("1");
            return list;
        }).thenApplyAsync(data->{
            println(data);
            return data+"1";
        }).thenAccept(data->{
            System.out.println(data);
        }).thenRun(()->{

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
