package com.chapter.distributed.zookeeper.api.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author zhengshijun
 * @version created on 2018/9/3.
 */
public class UsingWatcherSample {

    public static void main(String[] args) throws Exception{


        RetryPolicy retryPolicy = new ExponentialBackoffRetry(10000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(retryPolicy)
//                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .namespace("")
                .build();
        client.start();

        // 指定路径设置监听
        client.getData().usingWatcher(new Watcher() {
            @Override
            public void process(WatchedEvent event) {

                System.out.println(event);

            }
        }).forPath("/LOCK");


        System.out.println("等待");
        Thread.currentThread().join();

    }
}
