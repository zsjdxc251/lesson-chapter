package com.lesson.concurrent.api.sample.atomic;

import java.util.Random;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;


/**
 *
 *     LongAccumulator 初始化是设置一个规则 两个参数传入是通过自己规则存储一个数
 *     oldValue or newValue
 *     第二个参数设置初始值
 *     {@code accumulate} 设置值方法
 *     示例 ： 多线程 随机参数1000个数去进行设置 最后保存最大的值
 *
 *
 *    {@link DoubleAccumulator} extends {@code Striped64}
 *    {@link LongAccumulator} extends {@code Striped64}
 *    {@link DoubleAdder} extends {@code Striped64}
 *    {@link LongAdder} extends {@code Striped64}
 *
 *    {@code Striped64}
 * @author zhengshijun
 * @version created on 2018/9/4.
 */
public class Striped64Sample {

    public static void main(String[] args) throws Exception{
        LongAccumulator accumulator = new LongAccumulator(Long::max, Long.MIN_VALUE);
//        Thread[] ts = new Thread[1000];
//
//        for (int i = 0; i < 1000; i++) {
//            ts[i] = new Thread(() -> {
//                Random random = new Random();
//                long value = random.nextLong();
//                accumulator.accumulate(value); // 比较value和上一次的比较值，然后存储较大者
//            });
//            ts[i].start();
//        }
//        for (int i = 0; i < 1000; i++) {
//            ts[i].join();
//        }
        System.out.println(accumulator.longValue());


    }
}
