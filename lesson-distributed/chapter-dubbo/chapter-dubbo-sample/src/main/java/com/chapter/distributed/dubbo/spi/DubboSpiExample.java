package com.chapter.distributed.dubbo.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionFactory;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.*;
import com.alibaba.dubbo.rpc.cluster.Cluster;
import com.chapter.distributed.dubbo.service.IUserInfoService;
import com.chapter.distributed.dubbo.spi.custom.IProductService;

import java.sql.Driver;

/**
 * @author zhengshijun
 * @version created on 2018/6/21.
 */
public class DubboSpiExample {

    public static void main(String[] args){
        //Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();//.getExtension("customProtocol");


//       Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("dubbo");
//        URL url = new URL("dubbo","",20880);
//        Invoker<?> invoker = protocol.refer(IUserInfoService.class,url);
//        System.out.println(invoker.getClass());


        //System.out.println(invoker.invoke(new RpcInvocation()));
//        System.out.println(protocol.getDefaultPort());
//        IProductService driver = ExtensionLoader.getExtensionLoader(IProductService.class).getAdaptiveExtension();//.getExtension("customProtocol");
//        System.out.println(driver.getClass());

//        IProductService driver = ExtensionLoader.getExtensionLoader(IProductService.class).getAdaptiveExtension();
//        System.out.println(driver.getClass());


//        Cluster cluster = ExtensionLoader.getExtensionLoader(Cluster.class).getAdaptiveExtension();
//        System.out.println(cluster.getClass());


//        IProductService driver = ExtensionLoader.getExtensionLoader(IProductService.class).getAdaptiveExtension();
//        System.out.println(driver.getName("22",URL.valueOf("http").addParameter("i.product.service","i.product.service")));


//        ExtensionFactory extensionFactory = ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getAdaptiveExtension();
//        System.out.println(extensionFactory.getClass());

//        Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
//
//        System.out.println(protocol.getClass());
//
//        ProxyFactory proxyFactory = ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
//
//        System.out.println(proxyFactory);




        IProductService productService = ExtensionLoader.getExtensionLoader(IProductService.class).getAdaptiveExtension();

        System.out.println(productService.getClass());



//        Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
//        System.out.println(protocol.getClass());
    }
}
