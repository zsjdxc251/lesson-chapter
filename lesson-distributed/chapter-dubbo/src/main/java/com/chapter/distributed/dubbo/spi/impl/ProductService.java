package com.chapter.distributed.dubbo.spi.impl;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.chapter.distributed.dubbo.spi.custom.IProductService;

/**
 * @author zhengshijun
 * @version created on 2018/6/21.
 */
@Adaptive
public class ProductService implements IProductService {

    @Override
    public String getName(String name,URL url) {
        return "张";
    }
}
