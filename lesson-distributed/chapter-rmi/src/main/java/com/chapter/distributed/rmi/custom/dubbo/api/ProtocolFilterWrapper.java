package com.chapter.distributed.rmi.custom.dubbo.api;

import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2018/7/3.
 */
public class ProtocolFilterWrapper implements Protocol {

    private static final Logger log = LoggerFactory.getLogger(ProtocolFilterWrapper.class);
    private final Protocol protocol;

    public ProtocolFilterWrapper(Protocol protocol) {
        if (protocol == null) {
            throw new IllegalArgumentException("protocol == null");
        }
        this.protocol = protocol;
    }
    @Override
    public int getDefaultPort() {
        return 0;
    }


    private static <T> Invoker<T> buildInvokerChain(final Invoker<T> invoker) {
        Invoker<T> last = invoker;
        List<Filter> filters = Lists.newArrayList();
        filters.add(new Filter() {
            @Override
            public Result invoke(Invoker<?> invoker, Invocation invocation) throws Exception {
                log.info("1 filter invoke");
                return invoker.invoke(invocation);
            }
        });
        filters.add(new Filter() {
            @Override
            public Result invoke(Invoker<?> invoker, Invocation invocation) throws Exception {
                log.info("2 filter invoke");
                return invoker.invoke(invocation);
            }
        });
        if (filters.size() > 0) {
            for (int i = filters.size() - 1; i >= 0; i--) {
                final Filter filter = filters.get(i);
                final Invoker<T> next = last;
                final int index = i;
                last = new Invoker<T>() {

                    public Class<T> getInterface() {
                        return invoker.getInterface();
                    }

                    public URL getUrl() {
                        return new URL("filter -"+index);
                    }

                    public boolean isAvailable() {
                        return false;
                    }

                    public Result invoke(Invocation invocation) throws Exception {
                        log.info("invoke number");
                        return filter.invoke(next, invocation);
                    }

                    public void destroy() {

                    }
                    @Override
                    public String toString() {
                        return invoker.toString();
                    }
                };

            }
        }
        System.out.println(last.getUrl().getName());
        return last;
    }
    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws Exception {

        return protocol.export(buildInvokerChain(invoker));
    }

    @Override
    public <T> Invoker<T> refer(Class<T> type, URL url) throws Exception {
        return buildInvokerChain(protocol.refer(type, url));
    }

    @Override
    public void destroy() {

    }
}
