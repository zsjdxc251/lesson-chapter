package com.lesson.concurrent.api.sample.simple;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CasExample {

     private volatile int size;
     private volatile long length;
     private volatile Integer age;
    public CasExample(int size){
        this.size = size;
    }
    public int getSize(){
        return size;
    }


    AtomicInteger atomicInteger = new AtomicInteger();
    AtomicLong atomicLong = new AtomicLong();
    AtomicBoolean atomicBoolean = new AtomicBoolean();
    AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

    AtomicReference<String> atomicReference = new AtomicReference<>();

    AtomicReferenceArray<String> atomicReferenceArray = new AtomicReferenceArray<String>(10);
    AtomicLongArray atomicLongArray = new AtomicLongArray(10);
    AtomicLongFieldUpdater atomicLongFieldUpdater = AtomicLongFieldUpdater.newUpdater(CasExample.class,"length");

    AtomicReferenceFieldUpdater<CasExample,Integer> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(CasExample.class,Integer.class,"age");

    /**
     * 是否有更改过值
     */
    AtomicMarkableReference<String> atomicMarkableReference = new AtomicMarkableReference<>("",true);
    /**
     * 携带一个版本号
     */
    AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("",0);


    public static void exampleAtomicLongFieldUpdater(){


        // 新建AtomicLongFieldUpdater对象，传递参数是“class对象”和“long类型在类中对应的名称”
        AtomicIntegerFieldUpdater<CasExample> mAtoLong = AtomicIntegerFieldUpdater.newUpdater(CasExample.class, "size");
        CasExample person = new CasExample(123);

        // 比较person的"id"属性，如果id的值为12345678L，则设置为1000。
        mAtoLong.compareAndSet(person, 12322, 1000);
        System.out.println("id="+person.getSize());

    }



    public static void main(String[] args){
        exampleAtomicLongFieldUpdater();
    }


}
