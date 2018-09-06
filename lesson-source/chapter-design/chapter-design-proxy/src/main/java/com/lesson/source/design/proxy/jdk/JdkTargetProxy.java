package com.lesson.source.design.proxy.jdk;

import java.lang.reflect.Proxy;

public class JdkTargetProxy {


    public static <T> T getInstance(Class<T> target) {


        return (T)Proxy.newProxyInstance(target.getClassLoader(),
                new Class[]{target},new ProcessInvocationHandler());

    }
 }
