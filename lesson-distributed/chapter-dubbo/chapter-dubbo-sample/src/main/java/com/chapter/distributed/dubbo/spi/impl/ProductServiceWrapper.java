package com.chapter.distributed.dubbo.spi.impl;

import com.alibaba.dubbo.common.URL;
import com.chapter.distributed.dubbo.spi.custom.IProductService;

/**
 * @author zhengshijun
 * @version created on 2018/6/21.
 */

public class ProductServiceWrapper implements IProductService {

    private IProductService productService;

    public ProductServiceWrapper(IProductService productService) {

        this.productService = productService;

    }

    @Override
    public String getName(String name,URL url) {
        return "wrapper";
    }
}
