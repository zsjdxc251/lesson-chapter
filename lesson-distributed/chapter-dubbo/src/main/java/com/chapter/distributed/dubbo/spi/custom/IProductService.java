package com.chapter.distributed.dubbo.spi.custom;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * @author zhengshijun
 * @version created on 2018/6/21.
 */
@SPI("productService")
public interface IProductService {


    String getName(String name,URL url);
}
