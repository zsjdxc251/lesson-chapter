package com.chapter.distributed.rmi.custom.dubbo.client;

import com.chapter.distributed.rmi.custom.dubbo.Result;

import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/6/26.
 */
public class RpcResult implements Result {
    private Object result;
    private Throwable exception;


    public RpcResult(Object result) {
        this.result = result;
    }

    public RpcResult(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public Throwable getException() {
        return null;
    }

    @Override
    public boolean hasException() {
        return false;
    }

    @Override
    public Object recreate() throws Throwable {
        if (exception != null) {
            throw exception;
        }
        return result;
    }

    @Override
    public Object getResult() {
        return result;
    }

    @Override
    public Map<String, String> getAttachments() {
        return null;
    }

    @Override
    public String getAttachment(String key) {
        return null;
    }

    @Override
    public String getAttachment(String key, String defaultValue) {
        return null;
    }
}
