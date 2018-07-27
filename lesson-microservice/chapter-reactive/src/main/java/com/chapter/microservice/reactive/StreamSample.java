package com.chapter.microservice.reactive;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhengshijun
 * @version created on 2018/7/27.
 */
public class StreamSample {



    public static void main(String[] args){

        IntStream.range(0,20).filter(n->n%2==1).map(x->x+1).reduce(Integer::sum).ifPresent(System.out::println);

    }
}
