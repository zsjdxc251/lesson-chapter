package com.lesson.concurrent.api.sample;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 *
 *  {@link ThreadLocalRandom}
 *
 *  <li>
 *     {@code Math.random()}
 *     产生并发随机数 {@code ThreadLocalRandom}
 *  <li/>
 *
 * @author zhengshijun
 * @version created on 2018/8/28.
 */
public class ThreadLocalRandomSample {
    static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String[] args){

        System.out.println(random.nextInt(1,100));


        // 产生批量随机数
        random.ints().forEach(System.out::println);


    }

}
