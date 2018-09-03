package com.chapter.distributed.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;


/**
 * @author zhengshijun
 * @version created on 2018/9/3.
 */
public class DistributedLock implements Lock, Watcher {

    private ZooKeeper zooKeeper;

    private ConcurrentMap<Thread,Node> concurrentMap;
    private ConcurrentMap<String,Thread> waitThreadMap;

    private static final String LOCK_BASE_PATH="/locks";

    private static class Node {
        private String lockPath;
        private String preLockPath;

        public Node(String lockPath) {
            this.lockPath = lockPath;
        }

        public String getLockPath() {
            return lockPath;
        }

        public void setLockPath(String lockPath) {
            this.lockPath = lockPath;
        }

        public String getPreLockPath() {
            return preLockPath;
        }

        public void setPreLockPath(String preLockPath) {
            this.preLockPath = preLockPath;
        }
    }

    public DistributedLock() {
        try {
            this.zooKeeper = new ZooKeeper("127.0.0.1:2181",5000,this);
            Stat stat = zooKeeper.exists(LOCK_BASE_PATH,false);
            if (stat == null){
                zooKeeper.create(LOCK_BASE_PATH,null,ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            }
            concurrentMap = new ConcurrentHashMap<>();
            waitThreadMap = new ConcurrentHashMap<>();
        } catch (IOException | KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        if (tryLock()){
            return;
        }
        waitForLock();
    }

    private void waitForLock(){
        Thread currentThread = Thread.currentThread();
        Node node = concurrentMap.get(currentThread);
        String preLockPath = node.getPreLockPath();
        try {
            Stat stat = zooKeeper.exists(preLockPath,true);
            if (stat != null){
                System.out.println(node.getLockPath()+"节点等待"+node.getPreLockPath()+"释放锁");
                waitThreadMap.put(preLockPath,currentThread);
                LockSupport.park();
                System.out.println(node.getLockPath()+"获取锁");
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        Thread currentThread = Thread.currentThread();
        if (concurrentMap.containsKey(currentThread)){

        } else {

            try {
                String lockPath = zooKeeper.create(LOCK_BASE_PATH+"/",null,
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);

                concurrentMap.put(currentThread,new Node(lockPath));

                List<String> childrens= zooKeeper.getChildren(LOCK_BASE_PATH,false);
                if (childrens.size() == 1){
                    return true;
                }
                childrens.sort(Comparator.comparing(String::toString));
                System.out.println("childrens:{}"+childrens);
                SortedSet<String> sortedSet = childrens.stream().map(path->LOCK_BASE_PATH+"/"+path).collect(Collectors.toCollection(TreeSet::new));


                childrens = new ArrayList<>(sortedSet);

                int lockPathIndex = childrens.indexOf(lockPath);
                if (lockPathIndex == 0 ){
                    return true;
                }
                String preLockPath = childrens.get(lockPathIndex-1);

                concurrentMap.get(currentThread).setPreLockPath(preLockPath);

            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

        Thread currentThread = Thread.currentThread();

        Node node = concurrentMap.get(currentThread);
        try {
            Stat stat = zooKeeper.exists(node.getLockPath(),false);
            if (stat != null){
                zooKeeper.delete(node.getLockPath(),stat.getVersion());
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getType() == Event.EventType.None){
            // 连接成功
        } else if (watchedEvent.getType() == Event.EventType.NodeDeleted){
            // 删除节点

            String lockPath = watchedEvent.getPath();
            System.out.println(lockPath+"释放锁");
            Thread thread = waitThreadMap.get(lockPath);
            if (thread != null){
                LockSupport.unpark(thread);
            }

        }
    }
}
