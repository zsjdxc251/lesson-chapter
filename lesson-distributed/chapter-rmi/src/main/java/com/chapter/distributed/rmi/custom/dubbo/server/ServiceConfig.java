package com.chapter.distributed.rmi.custom.dubbo.server;

import com.chapter.distributed.rmi.custom.dubbo.JavassistProxyFactory;
import com.chapter.distributed.rmi.custom.dubbo.client.Invoker;

import java.util.ArrayList;
import java.util.List;


public class ServiceConfig<T> {


    T ref;

    Class<?> interfaceClass;

    public ServiceConfig(T ref, Class<?> interfaceClass) {
        this.ref = ref;
        this.interfaceClass = interfaceClass;
    }



    private static JavassistProxyFactory javassistProxyFactory = new JavassistProxyFactory();

    private static DubboProtocol dubboProtocol = new DubboProtocol();


    private final List<Exporter<?>> exporters = new ArrayList<Exporter<?>>();

    public void doExportUrlsFor1Protocol(){


        Invoker<T> invoker = javassistProxyFactory.getInvoker(ref,(Class<T>) interfaceClass);
        try {
            Exporter<?> exporter = dubboProtocol.export(invoker);
            exporters.add(exporter);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
