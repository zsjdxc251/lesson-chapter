package com.chapter.distributed.dubbo.spi.impl;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.RpcException;

/**
 * @author zhengshijun
 * @version created on 2018/6/21.
 */
public class CustomProtocol implements Protocol {
    @Override
    public int getDefaultPort() {
        return 8888;
    }

    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        return null;
    }

    @Override
    public <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException {
        return null;
    }

    @Override
    public void destroy() {

    }
}
