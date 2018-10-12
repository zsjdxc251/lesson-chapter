package com.lesson.source.pattern.proxy.jdk;

import com.lesson.source.pattern.proxy.IUserInfoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProcessInvocationHandler implements InvocationHandler {


    private IUserInfoService userInfoService;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println(method.getName());


        Object result = null;
        if (userInfoService != null){
            result =  method.invoke(userInfoService,args);
        }

        return result;
    }
}
