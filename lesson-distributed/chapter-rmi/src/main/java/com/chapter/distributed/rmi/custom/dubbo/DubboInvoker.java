package com.chapter.distributed.rmi.custom.dubbo;

import com.chapter.distributed.rmi.custom.dubbo.client.Client;
import com.chapter.distributed.rmi.custom.dubbo.client.Invocation;
import com.chapter.distributed.rmi.custom.dubbo.client.Invoker;
import com.chapter.distributed.rmi.custom.dubbo.client.RpcInvocation;

import java.io.Serializable;
import java.util.Set;

/**
 * @author zhengshijun
 * @version created on 2018/6/28.
 */
public class DubboInvoker<T> extends AbstractInvoker<T> implements Serializable{
    private final Set<Invoker<?>> invokers;



    public DubboInvoker(Class<T> serviceTyp,Set<Invoker<?>> invokers) {

        super(serviceTyp);
        this.invokers = invokers;
    }

    @Override
    protected Result doInvoke(Invocation invocation) throws Throwable {
        RpcInvocation inv = (RpcInvocation) invocation;
        Client client = new Client();
        return client.request(inv);

    }
}
