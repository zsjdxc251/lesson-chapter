package com.chapter.common;

import org.apache.commons.lang3.StringUtils;

import java.util.function.IntFunction;
import java.util.stream.Stream;

public class StreamTest {



    public static void main(String[] args){

        String[] names = {"1","2","3"};

        Integer i[] = Stream.of(names).map(Integer::parseInt).toArray(
                value -> new Integer[value]
        );



        System.out.println(StringUtils.join(i,","));

    }
}
