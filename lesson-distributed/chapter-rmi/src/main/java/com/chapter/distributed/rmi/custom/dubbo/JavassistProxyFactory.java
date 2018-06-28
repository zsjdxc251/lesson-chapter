package com.chapter.distributed.rmi.custom.dubbo;

import com.chapter.distributed.rmi.custom.dubbo.client.Invoker;
import com.chapter.distributed.rmi.custom.dubbo.client.InvokerInvocationHandler;

import java.lang.reflect.Proxy;

public class JavassistProxyFactory {

    public <T> Invoker<T> getInvoker(T proxy, Class<T> type) {
        // TODO Wrapper类不能正确处理带$的类名

        return new AbstractProxyInvoker<T>(proxy, type) {
            @Override
            public Object doInvoke(T proxy, String methodName,
                                      Class<?>[] parameterTypes,
                                      Object[] arguments) throws Throwable {
                return null;
            }
        };
    }

    public <T> T getProxy(Invoker<T> invoker, Class<T> interfaces) {

        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),
                new Class[]{interfaces},new InvokerInvocationHandler(invoker));
    }

}
