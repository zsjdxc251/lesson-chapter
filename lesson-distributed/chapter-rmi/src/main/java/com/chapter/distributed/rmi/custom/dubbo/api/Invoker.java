package com.chapter.distributed.rmi.custom.dubbo.api;

/**
 * @author zhengshijun
 * @version created on 2018/7/3.
 */
public interface Invoker<T> {
    /**
     * get service interface.
     *
     * @return service interface.
     */
    Class<T> getInterface();


    Result invoke(Invocation invocation) throws Exception;

    URL getUrl();

}
