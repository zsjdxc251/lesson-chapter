package com.chapter.distributed.zookeeper.rpc;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author zhengshijun
 * @version created on 2018/9/11.
 */
public class ZookeeperFactory {


    private static final String HOST_PORT_ARRAY= "127.0.0.1:2181";
    public static CuratorFramework createCuratorFramework(){

        CuratorFramework curatorFramework= CuratorFrameworkFactory.builder().
                connectString(HOST_PORT_ARRAY).
                sessionTimeoutMs(4000).namespace("registerCenter").
                retryPolicy(new ExponentialBackoffRetry(1000,
                        10)).build();
        curatorFramework.start();

        return curatorFramework;
    }
}
