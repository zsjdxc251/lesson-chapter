package com.chapter.distributed.zookeeper.api.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author zhengshijun
 * @version created on 2018/9/3.
 */
public class DistributedLockSample {

    public static void main(String[] args){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(10000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(3000)
                .build();
        client.start();



        InterProcessMutex interProcessMutex=new InterProcessMutex(client,"/locks");
        try {
            interProcessMutex.acquire();

            interProcessMutex.release();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
