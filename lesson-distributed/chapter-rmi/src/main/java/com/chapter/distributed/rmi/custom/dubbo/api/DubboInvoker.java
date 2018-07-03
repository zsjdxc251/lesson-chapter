package com.chapter.distributed.rmi.custom.dubbo.api;

/**
 * @author zhengshijun
 * @version created on 2018/7/3.
 */
public class DubboInvoker<T> implements Invoker<T> {
    private final URL url;
    private final Class<T> type;

    public DubboInvoker(URL url, Class<T> type) {
        this.url = url;
        this.type = type;
    }

    @Override
    public Class<T> getInterface() {
        return null;
    }

    @Override
    public Result invoke(Invocation invocation) throws Exception {
        return new RpcResult("数据 客户端");
    }

    @Override
    public URL getUrl() {
        return new URL("dubbo invoker");
    }
}
