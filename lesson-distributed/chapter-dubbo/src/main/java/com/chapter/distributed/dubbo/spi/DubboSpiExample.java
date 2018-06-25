package com.chapter.distributed.dubbo.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionFactory;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.chapter.distributed.dubbo.spi.custom.IProductService;

import java.sql.Driver;

/**
 * @author zhengshijun
 * @version created on 2018/6/21.
 */
public class DubboSpiExample {

    public static void main(String[] args){
//        Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();//.getExtension("customProtocol");


       Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("dubbo");
        System.out.println(protocol.getClass());
//        System.out.println(protocol.getDefaultPort());
//        IProductService driver = ExtensionLoader.getExtensionLoader(IProductService.class).getAdaptiveExtension();//.getExtension("customProtocol");
//        System.out.println(driver.getClass());

        IProductService driver = ExtensionLoader.getExtensionLoader(IProductService.class).getAdaptiveExtension();
        System.out.println(driver.getClass());


//        IProductService driver = ExtensionLoader.getExtensionLoader(IProductService.class).getAdaptiveExtension();
//        System.out.println(driver.getName("22",URL.valueOf("http").addParameter("i.product.service","i.product.service")));


//        ExtensionFactory extensionFactory = ExtensionLoader.getExtensionLoader(ExtensionFactory.class).getAdaptiveExtension();
//        System.out.println(extensionFactory.getClass());

    }
}
