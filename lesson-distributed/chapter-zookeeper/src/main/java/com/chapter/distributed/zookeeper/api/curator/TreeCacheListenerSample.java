package com.chapter.distributed.zookeeper.api.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 *
 *
 *    对指定路径下面节点监听 添加 删除 数据修改
 * @author zhengshijun
 * @version created on 2018/11/21.
 */
public class TreeCacheListenerSample {


    public static void main(String[] args) throws Exception{

        RetryPolicy retryPolicy = new ExponentialBackoffRetry(10000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2181")
                .retryPolicy(retryPolicy)

                .connectionTimeoutMs(3000)
                .namespace("")
                .build();
        client.start();


        TreeCache cache = new TreeCache(client, "/");



        try {
            cache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cache.getListenable().addListener((client1, event) -> {


            System.out.println(event);
        });

        Thread.currentThread().join();

    }


}
