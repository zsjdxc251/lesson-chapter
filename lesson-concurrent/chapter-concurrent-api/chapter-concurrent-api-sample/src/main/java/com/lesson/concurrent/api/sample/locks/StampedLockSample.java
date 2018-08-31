package com.lesson.concurrent.api.sample.locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 *
 *
 *     <p>
 *         {@code ReentrantReadWriteLock} 锁的升级版 {@code StampedLock}
 *         在 {@code StampedLock} 中使用乐观锁提高读写性能
 *     <p/>
 *
 *     {@link ReentrantReadWriteLock}
 *     {@link StampedLock}
 * @author zhengshijun
 * @version created on 2018/8/31.
 */
public class StampedLockSample {

    static StampedLock stampedLock = new StampedLock();


    public static void main(String[] args){




    }
}
