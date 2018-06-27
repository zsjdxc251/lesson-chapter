package com.chapter.distributed.rmi.custom.dubbo;

import com.chapter.distributed.rmi.custom.dubbo.client.Invocation;
import com.chapter.distributed.rmi.custom.dubbo.client.Invoker;

public abstract class AbstractProxyInvoker<T> implements Invoker<T> {

    private final T proxy;

    private final Class<T> type;


    public AbstractProxyInvoker(T proxy, Class<T> type) {
        this.proxy = proxy;
        this.type = type;
    }

    public Class<T> getInterface() {
        return null;
    }

    public Result invoke(Invocation invocation) throws Exception {
        return null;
    }


    public abstract Object doInvoke(T proxy, String methodName, Class<?>[] parameterTypes, Object[] arguments) throws Throwable ;
}
