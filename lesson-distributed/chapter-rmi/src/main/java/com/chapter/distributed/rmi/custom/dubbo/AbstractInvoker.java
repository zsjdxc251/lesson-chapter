package com.chapter.distributed.rmi.custom.dubbo;

import com.chapter.distributed.rmi.custom.dubbo.client.Invocation;
import com.chapter.distributed.rmi.custom.dubbo.client.Invoker;
import com.chapter.distributed.rmi.custom.dubbo.client.RpcInvocation;
import com.chapter.distributed.rmi.custom.dubbo.client.RpcResult;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhengshijun
 * @version created on 2018/6/28.
 */
public abstract class AbstractInvoker<T> implements Invoker<T>,Serializable {

    private final Class<T> type;

    private AtomicBoolean destroyed = new AtomicBoolean(false);

    public AbstractInvoker(Class<T> type) {
        this.type = type;
    }


    @Override
    public Class<T> getInterface() {
        return type;
    }

    @Override
    public Result invoke(Invocation inv) throws Exception {
        if (destroyed.get()) {
            throw new Exception();
        }
        RpcInvocation invocation = (RpcInvocation) inv;
        invocation.setInvoker(this);
        try {
            return doInvoke(invocation);
        } catch (Throwable e) {
            return new RpcResult(e);
        }
    }

    protected abstract Result doInvoke(Invocation invocation) throws Throwable;
}
