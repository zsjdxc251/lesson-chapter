package com.chapter.distributed.rmi.custom.dubbo.api;

/**
 * @author zhengshijun
 * @version created on 2018/7/3.
 */
public class RpcResult implements Result {

    private final Object value;

    public RpcResult(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
