package com.chapter.distributed.rmi.custom.dubbo.client;

import com.chapter.distributed.rmi.custom.client.RemoteInvocationHandler;

import java.lang.reflect.Proxy;

/**
 * @author zhengshijun
 * @version created on 2018/6/26.
 */
public class DubboConsumerProxy {

    /**
     * 创建客户端的远程代理。通过远程代理进行访问
     * @param interfaceCls
     * @param host
     * @param port
     * @param <T>
     * @return
     */
    public static  <T> T clientProxy(final Class<T>
                                             interfaceCls,
                                     final String host,final int port){

        Directory<T> directory = new RegistryDirectory<T>(interfaceCls);

        Invoker<T> invoker = new FailoverInvoker<T>(directory);

        T result = null;
        try {
            result = (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(),
                    new Class[]{interfaceCls},new InvokerInvocationHandler(invoker));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
