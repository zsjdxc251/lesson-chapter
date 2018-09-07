package com.lesson.source.pattern.proxy.custom;

import com.lesson.source.pattern.proxy.jdk.ProcessInvocationHandler;

public class CustomTargetProxy {

    public static <T> T getInstance(Class<T> target) {


        return (T) CustomProxy.newProxyInstance(target.getClassLoader(),
                new Class[]{target},new ProcessInvocationHandler());

    }
}
