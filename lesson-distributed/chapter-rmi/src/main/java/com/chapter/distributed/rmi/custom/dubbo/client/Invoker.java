package com.chapter.distributed.rmi.custom.dubbo.client;

import com.chapter.distributed.rmi.custom.dubbo.Result;

import java.io.Serializable;

/**
 * @author zhengshijun
 * @version created on 2018/6/26.
 */
public interface Invoker<T> extends Serializable {


    /**
     * get service interface.
     *
     * @return service interface.
     */
    Class<T> getInterface();


    Result invoke(Invocation invocation) throws Exception;
}
