package com.chapter.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InheritableThreadLocalTest {

    private static final Logger log = LoggerFactory.getLogger(InheritableThreadLocalTest.class);

    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();



    public static void main(String[] args){


        threadLocal.set("zsjdxc251");



        final Thread parentThread = Thread.currentThread();
        Thread thread = new Thread(()->{




            log.info("value:{}",threadLocal.get());


        });
        thread.start();
        try{
            thread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("value:{}",threadLocal.get());

    }





}
