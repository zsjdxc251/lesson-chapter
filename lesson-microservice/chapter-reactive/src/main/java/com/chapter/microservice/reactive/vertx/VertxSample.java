package com.chapter.microservice.reactive.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 */
public class VertxSample {

    public static void main(String[] args) throws Exception{

        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new AbstractVerticle() {
            @Override
            public void start(Future<Void> startFuture) throws Exception {
                System.out.println("Start");
                startFuture.complete();
            }

            @Override
            public void stop() throws Exception {
                System.out.println("Stop");
            }
        });
        // 实现定时器
        // 500 毫秒 执行打印
        vertx.setPeriodic(500, System.out::println);
        vertx.setPeriodic(500, System.out::println);
        vertx.setPeriodic(500, System.out::println);
        // 1000 毫秒 执行打印
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(() -> System.out.println("Hello,World"),
                1000, TimeUnit.MILLISECONDS);
        executorService.shutdown();


        Thread.currentThread().join();
        vertx.close();
    }
}
