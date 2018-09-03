package com.chapter.distributed.zookeeper;

import java.util.stream.IntStream;

/**
 * @author zhengshijun
 * @version created on 2018/9/3.
 */
public class DistributedLockTest {




    public static void main(String[] args){
        DistributedLock distributedLock = new DistributedLock();
        IntStream.range(0,10).forEach(i->{

            new Thread(()->{

                distributedLock.lock();



               // distributedLock.unlock();
            }).start();

        });

    }
}
