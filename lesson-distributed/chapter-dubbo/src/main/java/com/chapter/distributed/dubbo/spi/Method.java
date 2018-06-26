package com.chapter.distributed.dubbo.spi;

/**
 * @author zhengshijun
 * @version created on 2018/6/26.
 */
public class Method {

    public Object invokeMethod(Object o, String n, Class[] p, Object[] v) throws java.lang.reflect.InvocationTargetException {
        com.chapter.distributed.dubbo.service.IUserInfoService w;
        try {
            w = ((com.chapter.distributed.dubbo.service.IUserInfoService) o);
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
        try {
            if ("getName".equals(n) && p.length == 1) {
                return  w.getName((java.lang.String) v[0]);
            }
            if ("getAge".equals(n) && p.length == 1) {
                return  w.getAge(((Number) v[0]).intValue());
            }
        } catch (Throwable e) {
            throw new java.lang.reflect.InvocationTargetException(e);
        }
        throw new com.alibaba.dubbo.common.bytecode.NoSuchMethodException("Not found method \"" + n + "\" in class com.chapter.distributed.dubbo.service.IUserInfoService.");
    }
}
