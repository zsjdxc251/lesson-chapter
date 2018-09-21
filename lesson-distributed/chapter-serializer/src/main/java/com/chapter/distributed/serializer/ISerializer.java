package com.chapter.distributed.serializer;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public interface ISerializer {


    /**
     * 序列化
     * @param object
     * @param <T>
     * @return
     */
    <T> byte[] encoder(T object);

    /**
     * 反序列化
     * @param bytes
     * @param entry
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    <T> T decoder(byte[] bytes,Class<T> entry);



}
