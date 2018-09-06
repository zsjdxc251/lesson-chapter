package com.lesson.source.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;


public class CglibTargetProxy {


    public static <T> T getInstance(Class<T> target){

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(target);
        enhancer.setCallback(new ProcessMethodInterceptor());

        return (T)enhancer.create();
    }


}
