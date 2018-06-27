package com.chapter.distributed.rmi.custom.dubbo.server;

import com.chapter.distributed.rmi.custom.dubbo.client.Invoker;

public abstract class AbstractExporter<T> implements Exporter<T>{

    private final Invoker<T> invoker;

    private volatile boolean unexported = false;

    public AbstractExporter(Invoker<T> invoker) {
        if (invoker == null)
            throw new IllegalStateException("service invoker == null");
        if (invoker.getInterface() == null)
            throw new IllegalStateException("service type == null");
        this.invoker = invoker;
    }

    public Invoker<T> getInvoker() {
        return invoker;
    }

    public void unexport() {
        if (unexported) {
            return;
        }
        unexported = true;

    }

    public String toString() {
        return getInvoker().toString();
    }

}
