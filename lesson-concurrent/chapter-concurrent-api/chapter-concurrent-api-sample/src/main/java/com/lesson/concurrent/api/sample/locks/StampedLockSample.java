package com.lesson.concurrent.api.sample.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 *
 *
 *     <p>
 *         {@code ReentrantReadWriteLock} 锁的升级版 {@code StampedLock}
 *         在 {@code StampedLock} 中使用乐观锁提高读写性能
 *         读写锁在 多读写少的时候
 *     <p/>
 *
 *     {@link ReadWriteLock}
 *     {@link ReentrantReadWriteLock}
 *     {@link StampedLock}
 * @author zhengshijun
 * @version created on 2018/8/31.
 */
public class StampedLockSample {

    private StampedLock stampedLock = new StampedLock();

    private int minValue = 0;

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void main(String[] args){



    }




    /**
     *  使用 {@code StampedLock} 实现排它锁写锁
     *
     */
    private void stampedLockReentrantWriteLock(){

        // 获取锁
        long stamped = stampedLock.writeLock();

        try {
            minValue +=1;
        } finally {
            // 释放锁
            stampedLock.unlockWrite(stamped);
        }

    }

    /**
     *
     *  {@code StampedLock}
     *
     * 乐观读锁
     *
     * @return
     */
    private int stampedLockTryOptimisticRead(){
        // 尝试获取乐观锁
        long stamped = stampedLock.tryOptimisticRead();
        // 获取数据
        int minValue = this.minValue;

        // 检查是否被修改
        if (!stampedLock.validate(stamped)){
            try {
                // 强制使用 悲观读锁
                stamped = stampedLock.readLock();

                minValue = this.minValue;
            } finally {
                stampedLock.unlockRead(stamped);
            }

        }
        return minValue;
    }

    /**
     *
     * {@code StampedLock}
     *  悲观读锁 尝试转换为写锁
     *
     *
     * @return
     */
    public int readConverterWrite(){
        long stamped = stampedLock.readLock();
        int minValue = this.minValue;
        try {

            // 如果参数小于 0
            if (minValue < 0){
                long writeLockStamped = stampedLock.tryConvertToWriteLock(stamped);
                if (writeLockStamped != 0){
                    stamped = writeLockStamped;
                } else {
                    // 读锁升级写锁失败则释放读锁，显示获取独占写锁
                    stampedLock.unlockRead(stamped);

                    stamped = stampedLock.writeLock();
                }
                minValue = Integer.MAX_VALUE;
            }


        } finally {
            stampedLock.unlock(stamped);
        }
        return minValue;

    }











}
