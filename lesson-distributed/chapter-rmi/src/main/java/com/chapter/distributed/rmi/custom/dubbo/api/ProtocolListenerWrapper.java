package com.chapter.distributed.rmi.custom.dubbo.api;

/**
 * @author zhengshijun
 * @version created on 2018/7/3.
 */
public class ProtocolListenerWrapper implements Protocol {
    private final Protocol protocol;

    public ProtocolListenerWrapper(Protocol protocol) {
        if (protocol == null) {
            throw new IllegalArgumentException("protocol == null");
        }
        this.protocol = protocol;
    }
    @Override
    public int getDefaultPort() {
        return 0;
    }

    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws Exception {
        return new ListenerExporterWrapper<T>(protocol.export(invoker));
    }

    @Override
    public <T> Invoker<T> refer(Class<T> type, URL url) throws Exception {
        return new ListenerInvokerWrapper<T>(protocol.refer(type, url));
    }

    @Override
    public void destroy() {

    }
}
