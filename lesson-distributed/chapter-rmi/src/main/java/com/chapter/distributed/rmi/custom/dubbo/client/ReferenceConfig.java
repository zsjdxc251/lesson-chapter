package com.chapter.distributed.rmi.custom.dubbo.client;

import com.chapter.distributed.rmi.custom.dubbo.JavassistProxyFactory;
import com.chapter.distributed.rmi.custom.dubbo.server.DubboProtocol;

/**
 * @author zhengshijun
 * @version created on 2018/6/28.
 */
public class ReferenceConfig<T> {


    private static DubboProtocol dubboProtocol = new DubboProtocol();

    private transient volatile T ref;
    private Class<T> interfaceClass;
    private transient volatile Invoker<T> invoker;

    private static JavassistProxyFactory javassistProxyFactory = new JavassistProxyFactory();

    public ReferenceConfig(Class<T> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    public T get(){
        if (ref == null) {
            init();
        }
        return ref;
    }

    private void init(){

        ref = createProxy();


    }

    private T createProxy(){

        try {

            invoker = dubboProtocol.refer(interfaceClass);


        } catch (Exception e) {

            e.printStackTrace();
        }

        return javassistProxyFactory.getProxy(invoker,interfaceClass);
    }
}
