package com.chapter.distributed.rmi.custom.dubbo.api;

/**
 * @author zhengshijun
 * @version created on 2018/7/3.
 */
public class ListenerExporterWrapper<T> implements Exporter<T> {

    private final Exporter<T> exporter;
    public ListenerExporterWrapper(Exporter<T> exporter) {
        if (exporter == null) {
            throw new IllegalArgumentException("exporter == null");
        }
        this.exporter = exporter;
    }
    @Override
    public Invoker<T> getInvoker() {
        return exporter.getInvoker();
    }

    @Override
    public void unexport() {

    }
}
