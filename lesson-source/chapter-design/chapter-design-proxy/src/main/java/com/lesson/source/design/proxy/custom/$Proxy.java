package com.lesson.source.design.proxy.custom;

import java.lang.reflect.InvocationHandler;

public class $Proxy0 implements com.lesson.source.design.proxy.IUserInfoService {
    private InvocationHandler handler;

    public $Proxy0(InvocationHandler handler) {
        this.handler = handler;
    }

    public java.lang.String getName(java.lang.String arg0) {
        return handler.invoke(this, com.lesson.source.design.proxy.IUserInfoService.class.getMethod("getName", new Class[]{java.lang.String.class}), Object[]
        args)}
}


