package com.lesson.open.interfaces.design.chain;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhengshijun
 * @version created on 2018/9/26.
 */
public class Result {

    private AtomicInteger count = new AtomicInteger();

    public void increment(){
        count.incrementAndGet();
    }

    public int get(){
        return count.get();
    }
}
