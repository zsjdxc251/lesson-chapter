package com.lesson.source.mybatis.quickstart.plugins;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhengshijun
 * @version created on 2018/10/12.
 */
public class CustomPlugin implements InvocationHandler {

    private Object target;

    private Interceptor interceptor;

    public CustomPlugin(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    public static Object wrap(Object target, Interceptor interceptor) {

        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                getAllInterfaces(target.getClass()),
                new CustomPlugin(target, interceptor));
    }
    private static Class<?>[] getAllInterfaces(Class<?> type) {
        Set<Class<?>> interfaces = new HashSet<Class<?>>();
        while (type != null) {
            for (Class<?> c : type.getInterfaces()) {
                interfaces.add(c);
            }
            type = type.getSuperclass();
        }
        return interfaces.toArray(new Class<?>[interfaces.size()]);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (!Object.class.equals(method.getDeclaringClass())){
            interceptor.intercept(new Invocation(target,method,args));
        }
        return method.invoke(target,args);
    }
}
