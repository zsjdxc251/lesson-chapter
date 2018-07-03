package com.chapter.distributed.rmi.custom.dubbo.api;

/**
 * @author zhengshijun
 * @version created on 2018/7/3.
 */
public class DubboProtocol implements Protocol {
    @Override
    public int getDefaultPort() {
        return 0;
    }

    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws Exception {

        return new DubboExporter<T>(invoker);
    }

    @Override
    public <T> Invoker<T> refer(Class<T> type, URL url) throws Exception {


        return  new DubboInvoker<T>(url,type);
    }

    @Override
    public void destroy() {

    }
}
