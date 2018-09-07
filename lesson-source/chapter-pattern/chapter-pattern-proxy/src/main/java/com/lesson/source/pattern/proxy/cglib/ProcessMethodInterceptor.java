package com.lesson.source.pattern.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class ProcessMethodInterceptor implements MethodInterceptor {
    
    private static final Logger log = LoggerFactory.getLogger(ProcessMethodInterceptor.class);

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("method:{}",method);


        return proxy.invokeSuper(obj,args);
    }
}
