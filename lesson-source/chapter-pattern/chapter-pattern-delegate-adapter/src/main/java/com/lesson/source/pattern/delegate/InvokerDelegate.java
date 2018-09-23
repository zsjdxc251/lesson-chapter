package com.lesson.source.pattern.delegate;

/**
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class InvokerDelegate implements Invoker{

    private Invoker invoker;

    public InvokerDelegate(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void doInvoker(String params) {
        invoker.doInvoker(params);
    }
}
