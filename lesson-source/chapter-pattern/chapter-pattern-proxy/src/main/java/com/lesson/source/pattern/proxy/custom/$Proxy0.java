package com.lesson.source.pattern.proxy.custom;

import com.lesson.source.pattern.proxy.IUserInfoService;

import java.lang.reflect.InvocationHandler;

public class $Proxy0 implements IUserInfoService {
    private InvocationHandler handler;

    public $Proxy0(InvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public java.lang.String getName(java.lang.String arg0) {
        java.lang.String result = null;
        try {
            result = (java.lang.String) handler.invoke(this, IUserInfoService.class.getMethod("getName", java.lang.String.class), new Object[]{arg0});
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void setName(java.lang.String arg0, java.lang.Integer arg1) {
        try {
            handler.invoke(this, IUserInfoService.class.getMethod("setName", java.lang.String.class, java.lang.Integer.class), new Object[]{arg0, arg1});
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getAge() {
        int result = 0;
        try {
            result = (int) handler.invoke(this, IUserInfoService.class.getMethod("getAge"), new Object[]{});
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}











