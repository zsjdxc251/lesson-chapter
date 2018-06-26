package com.chapter.distributed.rmi.custom.dubbo.client;

import com.alibaba.fastjson.JSON;
import com.chapter.distributed.rmi.custom.dubbo.Result;
import com.chapter.distributed.rmi.service.ICustomRpcService;

/**
 * @author zhengshijun
 * @version created on 2018/6/26.
 */
public class FailoverInvoker<T> implements Invoker<T> {


    private final Directory<T> directory;


    public FailoverInvoker(Directory<T> directory) {
        this.directory = directory;
    }

    @Override
    public Class<T> getInterface() {
        return directory.getInterface();
    }

    @Override
    public Result invoke(Invocation invocation) throws Exception {







        return new RpcResult();
    }
}
