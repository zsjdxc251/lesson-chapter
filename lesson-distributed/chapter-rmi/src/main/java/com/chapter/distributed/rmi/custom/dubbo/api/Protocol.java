package com.chapter.distributed.rmi.custom.dubbo.api;

/**
 * @author zhengshijun
 * @version created on 2018/7/3.
 */
public interface Protocol {

    int getDefaultPort();

    <T> Exporter<T> export(Invoker<T> invoker) throws Exception;


    <T> Invoker<T> refer(Class<T> type, URL url) throws Exception;


    void destroy();

}
