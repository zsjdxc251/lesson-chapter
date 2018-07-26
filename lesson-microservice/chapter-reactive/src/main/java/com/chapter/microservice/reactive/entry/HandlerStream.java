package com.chapter.microservice.reactive.entry;

import java.util.function.Consumer;

public class HandlerStream<T> {


    private T t ;

    private final Consumer<T> consumer;

    public HandlerStream(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public static <T> HandlerStream<T> generate(Consumer<T> consumer){


        return new HandlerStream<>(consumer);
    }


    public void find(T t){
        consumer.accept(t);
    }
}
