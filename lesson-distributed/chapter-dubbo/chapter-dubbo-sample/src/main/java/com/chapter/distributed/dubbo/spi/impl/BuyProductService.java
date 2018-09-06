package com.chapter.distributed.dubbo.spi.impl;

import com.alibaba.dubbo.common.URL;
import com.chapter.distributed.dubbo.spi.custom.IProductService;

/**
 * @author zhengshijun
 * @version created on 2018/6/25.
 */
public class BuyProductService implements IProductService {

    @Override
    public String getName(String name, URL url) {
        return null;
    }
}
