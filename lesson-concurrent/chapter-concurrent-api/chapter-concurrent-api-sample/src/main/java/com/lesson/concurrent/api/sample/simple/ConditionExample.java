package com.lesson.concurrent.api.sample.simple;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {

    Lock lock = new ReentrantLock();

    Condition read = lock.newCondition();

    Condition write = lock.newCondition();

    LinkedList<String> linkedList = new LinkedList<>();

    public String get(){
        lock.lock();
        try {
            while (linkedList.isEmpty()) {
                read.await();
            }
            write.signal();
            return linkedList.getLast();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return null;
    }

    public void set(String value){
        lock.lock();
        try {
            while (linkedList.size() > 9) {
                write.await();
            }
            linkedList.addFirst(value);
            read.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
