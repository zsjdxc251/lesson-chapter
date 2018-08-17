package com.chapter.common;

public class UncaughtExceptionTest {

    public static void main(String[] args){



        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {

            System.out.println(e.getMessage());

        });
        throw new NullPointerException("空指针异常");
    }
}
