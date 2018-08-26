package com.chapter.common;

import sun.reflect.Reflection;

import java.util.stream.Stream;

public class StackTest {


    public static void main(String[] args){

        Thread thread = Thread.currentThread();


        StackTraceElement[] stackTraceElements = thread.getStackTrace();

        Stream.of(stackTraceElements).forEach(stackTraceElement -> {
            System.out.println(stackTraceElement.getClassName()+"-"+stackTraceElement.getMethodName()+"-"+
                    stackTraceElement.getLineNumber()+"-"+stackTraceElement.getFileName());
        });

        Reflection reflection;







    }
}
