package com.lesson.concurrent.api.sample.simple;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SemaphoreExample {

    private static final Logger log = LoggerFactory.getLogger(SemaphoreExample.class);

    private LinkedList<String> linkedList = new LinkedList<>();
    private final Semaphore useCount;
    private final Semaphore unUseCount;

    public SemaphoreExample(int count){

        useCount = new Semaphore(0);
        unUseCount = new Semaphore(count);
        for (int i=0;i<count;i++){
            linkedList.addLast("数据"+i);
        }
    }

    public String get() throws InterruptedException{
        String result = null;
        unUseCount.acquire();

        synchronized (linkedList) {

            result = linkedList.removeFirst();
        }
        useCount.release();
        return result;
    }

    public void set(String result) throws InterruptedException{
        useCount.acquire();
        synchronized (linkedList) {
            linkedList.addLast(result);
        }
        unUseCount.release();
    }


    public static void main(String[] args){
        SemaphoreExample semaphoreExample = new SemaphoreExample(2);
        IntStream.range(0,100).forEach(x->{
            new Thread(()->{
                String result = null;
                try {
                    result = semaphoreExample.get();
                    log.info("result:{}",result);
                    TimeUnit.MILLISECONDS.sleep((int)(Math.random()*10));


                    semaphoreExample.set(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }



            },"自定义线程"+x).start();
        });

    }
}
