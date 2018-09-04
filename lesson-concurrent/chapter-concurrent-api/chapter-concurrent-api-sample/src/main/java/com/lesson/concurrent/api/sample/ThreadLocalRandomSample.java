package com.lesson.concurrent.api.sample;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * {@link ThreadLocalRandom}
 *
 * <li>
 * {@code Math.random()}
 * 产生并发随机数 {@code ThreadLocalRandom}
 * <li/>
 *
 * @author zhengshijun
 * @version created on 2018/8/28.
 */
public class ThreadLocalRandomSample {
    static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String[] args) {

        IntStream.range(0, 10).parallel().forEach(i -> {
            new Thread(() -> {
                // 10个线程参数的随机数一样
                System.out.println(Thread.currentThread().getName() + "-->" + random.nextInt(1, 100));
            }).start();


        });

        // 产生批量随机数
        // random.ints().forEach(System.out::println);


    }

}
