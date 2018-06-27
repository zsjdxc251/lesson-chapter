package com.chapter.distributed.rmi.custom.dubbo.server;

import com.chapter.distributed.rmi.custom.dubbo.client.Invoker;

public interface Exporter<T> {

    /**
     * get invoker.
     *
     * @return invoker
     */
    Invoker<T> getInvoker();

    /**
     * unexport.
     * <p>
     * <code>
     * getInvoker().destroy();
     * </code>
     */
    void unexport();
}
