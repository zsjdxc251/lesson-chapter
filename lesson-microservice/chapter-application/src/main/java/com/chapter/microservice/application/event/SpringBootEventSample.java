package com.chapter.microservice.application.event;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengshijun
 * @version created on 2018/7/20.
 */
@EnableAutoConfiguration
public class SpringBootEventSample {

    public static void main(String[] args){
        AtomicInteger count = new AtomicInteger(0);
        new SpringApplicationBuilder(SpringBootEventSample.class).listeners(event -> {

            System.err.println(count.incrementAndGet()+"."+event.getClass().getName());
        }).web(WebApplicationType.NONE).run(args).close();


    }
}
