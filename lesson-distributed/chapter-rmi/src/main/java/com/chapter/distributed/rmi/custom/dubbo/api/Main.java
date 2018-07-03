package com.chapter.distributed.rmi.custom.dubbo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhengshijun
 * @version created on 2018/7/3.
 */
public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args){



        DubboProtocol dubboProtocol = new DubboProtocol();
        ProtocolFilterWrapper protocolFilterWrapper = new ProtocolFilterWrapper(dubboProtocol);
        ProtocolListenerWrapper protocolListenerWrapper = new ProtocolListenerWrapper(protocolFilterWrapper);


//        try {
//            Exporter<IUserInfoService> exporter =  protocolListenerWrapper.export(new Invoker<IUserInfoService>() {
//                @Override
//                public Class<IUserInfoService> getInterface() {
//                    return null;
//                }
//
//                @Override
//                public Result invoke(Invocation invocation) throws Exception {
//                    log.info("走数据");
//                    return new RpcResult("数据1");
//                }
//
//                @Override
//                public URL getUrl() {
//                    return new URL("main invoker");
//                }
//            });
//
//            System.out.println(exporter.getInvoker().getUrl().getName());
//            Result result = exporter.getInvoker().invoke(new RpcInvocation());
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        
        try {
            Invoker<IUserInfoService> invoker = protocolListenerWrapper.refer(IUserInfoService.class,new URL("main new url"));
            // get invoker
            //ListenerInvokerWrapper-> filter 1-> filter 2 -> dubbo invoker
            //ListenerInvokerWrapper(filter 1-> filter 2 -> dubbo invoker);
            System.out.println(invoker);
            System.out.println(invoker.invoke(new RpcInvocation()));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
