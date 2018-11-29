package com.lesson.distributed.netty.quickstart.netty.api;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/11/28.
 *
 * @see EventLoop
 */
public class EventLoopSample {

    public static void main(String[] args){


        EventLoopGroup eventExecutors = new NioEventLoopGroup(1);


        for (int i=0;i<100;i++) {
            eventExecutors.execute(()->{


                try {
                    System.out.println(Thread.currentThread().getName());
                    TimeUnit.SECONDS.sleep(5);

                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


    }
}
