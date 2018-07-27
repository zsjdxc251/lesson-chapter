package com.chapter.microservice.reactive;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author zhengshijun
 * @version created on 2018/7/27.
 */
public class ReactorSample {


    public static void main(String[] args){

        int[] numberArray = IntStream.range(0,20).toArray();

        Flux.just(0,1,2,3,4,5,6).filter(n-> n%2 ==1).reduce(Integer::sum).subscribeOn(Schedulers.immediate()).subscribe(System.out::println);
// Schedulers Subscribes
    }
}
