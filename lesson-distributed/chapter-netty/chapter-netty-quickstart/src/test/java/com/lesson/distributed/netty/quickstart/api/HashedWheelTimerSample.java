package com.lesson.distributed.netty.quickstart.api;

import io.netty.util.HashedWheelTimer;

import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/11/15.
 */
public class HashedWheelTimerSample {


    public static void main(String[] args){

        HashedWheelTimer hashedWheelTimer =

                new HashedWheelTimer(1,TimeUnit.SECONDS,10);


         hashedWheelTimer.start();

         hashedWheelTimer.newTimeout(timeout -> {
             System.out.println("1");
         },1,TimeUnit.SECONDS);
    }
}
