package com.chapter.microservice.reactive;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ForkJoinPool;
import org.springframework.util.concurrent.ListenableFuture;

/**
 *  {@link ListenableFuture}
 */
public class CompletableFutureSample {


    public static void main(String[] args){

        System.out.println(ForkJoinPool.commonPool().getParallelism());


        //ListenableFuture

    }
}
