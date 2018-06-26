package com.chapter.distributed.rmi.custom.dubbo.client;

import java.util.List;


public interface Directory<T>  {

    /**
     * get service type.
     *
     * @return service type.
     */
    Class<T> getInterface();

    /**
     * list invokers.
     *
     * @return invokers
     */
    List<Invoker<T>> list(Invocation invocation) throws Exception;

}
