package com.chapter.distributed.rmi.custom.dubbo.api;

/**
 * @author zhengshijun
 * @version created on 2018/7/3.
 */
public interface Filter {
    Result invoke(Invoker<?> invoker, Invocation invocation) throws Exception;
}
