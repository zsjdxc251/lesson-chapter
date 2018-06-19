package com.chapter.distributed.serializer;

import com.alibaba.fastjson.JSON;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class FastJsonSerializer implements ISerializer {

    @Override
    public <T> byte[] encoder(T object) {

        return JSON.toJSONString(object).getBytes();
    }

    @Override
    public <T> T decoder(byte[] bytes, Class<T> entry) {

        return JSON.parseObject(new String(bytes),entry);
    }
}
