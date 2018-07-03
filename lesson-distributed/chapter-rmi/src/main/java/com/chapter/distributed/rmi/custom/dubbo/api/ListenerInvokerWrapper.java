package com.chapter.distributed.rmi.custom.dubbo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhengshijun
 * @version created on 2018/7/3.
 */
public class ListenerInvokerWrapper<T> implements Invoker<T> {

    private static final Logger log = LoggerFactory.getLogger(ListenerInvokerWrapper.class);
    private final Invoker<T> invoker;

    public ListenerInvokerWrapper(Invoker<T> invoker) {
        if (invoker == null) {
            throw new IllegalArgumentException("invoker == null");
        }
        this.invoker = invoker;

    }
    @Override
    public Class<T> getInterface() {
        return null;
    }

    @Override
    public Result invoke(Invocation invocation) throws Exception {
        log.info("invoke");
        return invoker.invoke(invocation);
    }

    @Override
    public URL getUrl() {
        return null;
    }
}
