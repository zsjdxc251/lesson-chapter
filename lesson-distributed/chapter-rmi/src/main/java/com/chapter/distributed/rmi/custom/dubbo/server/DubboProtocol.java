package com.chapter.distributed.rmi.custom.dubbo.server;

import com.chapter.distributed.rmi.custom.dubbo.ExchangeHandler;
import com.chapter.distributed.rmi.custom.dubbo.ExchangeHandlerAdapter;
import com.chapter.distributed.rmi.custom.dubbo.client.Invocation;
import com.chapter.distributed.rmi.custom.dubbo.client.Invoker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DubboProtocol {

    protected final Map<String, Exporter<?>> exporterMap = new ConcurrentHashMap<String, Exporter<?>>();



    private ExchangeHandler requestHandler = new ExchangeHandlerAdapter() {

        public Object reply(Object message) throws Exception {
            if (message instanceof Invocation) {
                Invocation inv = (Invocation) message;

                Invoker<?> invoker = getInvoker(inv);


                return invoker.invoke(inv);
            }
            throw new Exception();

        }
    };


    private Invoker<?> getInvoker( Invocation inv) throws Exception {


        String serviceKey = inv.getInvoker().getInterface().getName();
        DubboExporter<?> exporter = (DubboExporter<?>)exporterMap.get(serviceKey);

        return exporter.getInvoker();
    }
    public <T> Exporter<T> export(Invoker<T> invoker) throws Exception {

        String key = invoker.getInterface().getName();
        DubboExporter<T> exporter = new DubboExporter<T>(invoker, key, exporterMap);
        exporterMap.put(key, exporter);
        openServer();
        return exporter;
    }
    private void openServer(){





    }

}
