package com.chapter.distributed.rmi.custom.dubbo;

import com.chapter.distributed.rmi.custom.dubbo.client.Invocation;
import com.chapter.distributed.rmi.custom.dubbo.client.Invoker;
import com.chapter.distributed.rmi.custom.dubbo.client.RpcResult;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractProxyInvoker<T> implements Invoker<T> {

    private final T proxy;

    private final Class<T> type;


    public AbstractProxyInvoker(T proxy, Class<T> type) {
        this.proxy = proxy;
        this.type = type;
    }

    public Class<T> getInterface() {
        return type;
    }

    public Result invoke(Invocation invocation) throws Exception {
        try {
            return new RpcResult(doInvoke(proxy, invocation.getMethodName(), invocation.getParameterTypes(), invocation.getArguments()));
        } catch (InvocationTargetException e) {
            return new RpcResult(e.getTargetException());
        } catch (Throwable e) {
            throw new Exception("Failed to invoke remote proxy method " + invocation.getMethodName() + " to , cause: " + e.getMessage(), e);
        }
    }


    public abstract Object doInvoke(T proxy, String methodName, Class<?>[] parameterTypes, Object[] arguments) throws Throwable ;
}
