package com.chapter.common;

/**
 * @author zhengshijun
 * @version created on 2018/10/26.
 */
public class ThreadTest {

    public static void main(String[] args) throws Exception{

        Thread thread = new Thread();

        thread.join();


        thread.wait();

        Thread.sleep(100);

        Thread.yield();

    }
}
