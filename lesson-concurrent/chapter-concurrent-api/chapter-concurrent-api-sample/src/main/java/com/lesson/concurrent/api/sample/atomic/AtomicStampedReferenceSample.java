package com.lesson.concurrent.api.sample.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *
 *
 *    通过版本号标记
 *
 *    {@link AtomicStampedReference}
 *
 *
 * @author zhengshijun
 * @version created on 2018/9/4.
 */
public class AtomicStampedReferenceSample {

    public static void main(String[] args){

        AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<>("value",1);


    }
}
