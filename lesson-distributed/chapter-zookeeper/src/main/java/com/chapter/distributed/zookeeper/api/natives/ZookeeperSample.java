package com.chapter.distributed.zookeeper.api.natives;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 *
 *   PERSISTENT - 持久化目录节点：客户端与zookeeper断开连接后，该节点依旧存在
 *   PERSISTENT_SEQUENTIAL - 持久化顺序编号目录节点：客户端与zookeeper断开连接后，该节点依旧存在，只是Zookeeper给该节点名称进行顺序编号
 *   EPHEMERAL - 临时目录节点：客户端与zookeeper断开连接后，该节点被删除
 *   EPHEMERAL_SEQUENTIAL - 临时顺序编号目录节点：客户端与zookeeper断开连接后，该节点被删除，只是Zookeeper给该节点名称进行顺序编号
 *
 * @author zhengshijun
 * @version created on 2018/8/31.
 */
public class ZookeeperSample {


    public static void main(String[] args){
        try {

            ZooKeeper zooKeeper = new ZooKeeper("localhost:2181",4000,watchedEvent -> {

                System.out.println(watchedEvent.getPath()+"-"+watchedEvent.getState()+"-"+watchedEvent.getType().name());
            });

//            Stat stat = zooKeeper.exists("/locks/",false);
//            if (stat == null){
//                zooKeeper.create("/locks/","".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
//            }

            zooKeeper.create("/locks/","".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);

            List<String> childrenUrls = zooKeeper.getChildren("/locks",false);

            childrenUrls.forEach(System.out::println);
            zooKeeper.close();

        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }

    }
}
