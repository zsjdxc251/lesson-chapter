package com.chapter.common;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Throwable {


        MethodHandles.Lookup lookup = MethodHandles.lookup();

        MethodHandle mh = lookup.findStatic(App.class,"test", MethodType.methodType(void.class));

        mh.invokeExact();

    }

    public static void test(){
        System.out.println("hello");
    }
}
