package com.lesson.source.pattern.iterator;

import java.util.Iterator;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class IteratorSample {

    public static void main(String[] args){

        Queue<String> queue = new Queue<>(10);

        Iterator<String> iterator = queue.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
