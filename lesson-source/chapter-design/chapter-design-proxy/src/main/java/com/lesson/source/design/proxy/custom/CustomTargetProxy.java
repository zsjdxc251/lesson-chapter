package com.lesson.source.design.proxy.custom;

import com.lesson.source.design.proxy.jdk.ProcessInvocationHandler;

import java.lang.reflect.Proxy;

public class CustomTargetProxy {

    public static <T> T getInstance(Class<T> target) {


        return (T) CustomProxy.newProxyInstance(target.getClassLoader(),
                new Class[]{target},new ProcessInvocationHandler());

    }
}
