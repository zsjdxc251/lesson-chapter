package com.chapter.distributed.dubbo.spi;


import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class IProductService$Adpative implements com.chapter.distributed.dubbo.spi.custom.IProductService {
    public java.lang.String getName(java.lang.String arg0, com.alibaba.dubbo.common.URL arg1) {
        if (arg1 == null) throw new IllegalArgumentException("url == null");
        com.alibaba.dubbo.common.URL url = arg1;
        String extName = url.getParameter("i.product.service");
        if (extName == null)
            throw new IllegalStateException("Fail to get extension(com.chapter.distributed.dubbo.spi.custom.IProductService) name from url(" + url.toString() + ") use keys([i.product.service])");
        com.chapter.distributed.dubbo.spi.custom.IProductService extension = (com.chapter.distributed.dubbo.spi.custom.IProductService) ExtensionLoader.getExtensionLoader(com.chapter.distributed.dubbo.spi.custom.IProductService.class).getExtension(extName);
        return extension.getName(arg0, arg1);
    }
}
