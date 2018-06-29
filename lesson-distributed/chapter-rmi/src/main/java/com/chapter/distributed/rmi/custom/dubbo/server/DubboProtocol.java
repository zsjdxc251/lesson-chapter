package com.chapter.distributed.rmi.custom.dubbo.server;

import com.chapter.distributed.rmi.custom.dubbo.DubboInvoker;
import com.chapter.distributed.rmi.custom.dubbo.ExchangeHandler;
import com.chapter.distributed.rmi.custom.dubbo.ExchangeHandlerAdapter;
import com.chapter.distributed.rmi.custom.dubbo.client.Invocation;
import com.chapter.distributed.rmi.custom.dubbo.client.Invoker;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class DubboProtocol {

    protected final Map<String, Exporter<?>> exporterMap = new ConcurrentHashMap<String, Exporter<?>>();
    private final Map<String, Server> serverMap = new ConcurrentHashMap<>();
    protected final Set<Invoker<?>> invokers = new HashSet<>();

    private ExchangeHandler requestHandler = new ExchangeHandlerAdapter() {

        public Object reply(Object message) throws Exception {
            if (message instanceof Invocation) {
                Invocation inv = (Invocation) message;

                Invoker<?> invoker = getInvoker(inv);
                // invoker = AbstractProxyInvoker

                System.out.println(invoker);
                return invoker.invoke(inv);
            }
            throw new Exception();

        }
    };


    private Invoker<?> getInvoker(Invocation inv) throws Exception {


        String serviceKey = inv.getInvoker().getInterface().getName();
        DubboExporter<?> exporter = (DubboExporter<?>) exporterMap.get(serviceKey);

        return exporter.getInvoker();
    }

    public <T> Exporter<T> export(Invoker<T> invoker) throws Exception {
        // invoker = AbstractProxyInvoker
        String key = invoker.getInterface().getName();
        DubboExporter<T> exporter = new DubboExporter<T>(invoker, key, exporterMap);
        exporterMap.put(key, exporter);
        openServer();
        return exporter;
    }

    private void openServer() {

        String key = "localhost:8080";
        Server server = null;
        if ((server = serverMap.get(key)) == null) {
            server = new Server(requestHandler);
            server.start();
            serverMap.putIfAbsent(key, server);
        }
    }


    public <T> Invoker<T> refer(Class<T> serviceType) throws Exception {
        // create rpc invoker.
        DubboInvoker<T> invoker = new DubboInvoker<T>(serviceType, invokers);
        invokers.add(invoker);
        return invoker;
    }

}
