package com.chapter.distributed.rmi.custom.dubbo.client;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2018/6/26.
 */
public class RegistryDirectory<T> implements Directory<T> {
    private final Class<T> serviceType;

    public RegistryDirectory(Class<T> serviceType) {
        this.serviceType = serviceType;
    }


    public Class<T> getInterface() {
        return null;
    }

    public List<Invoker<T>> list(Invocation invocation) throws Exception {
        return null;
    }
}
