package com.lesson.source.pattern.singleton.seriable;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class SeriableSample implements Serializable {

    private final static SeriableSample SERIABLE_SAMPLE = new SeriableSample();

    public static SeriableSample getInstance(){
        return SERIABLE_SAMPLE;
    }

    /**
     *   反序列化之后 防止单例被破坏
     *   不加该代码 {@code SerializationUtils#clone}对象是新对象
     * @return
     */
    private  Object readResolve(){
        return  SERIABLE_SAMPLE;
    }

    public static void main(String[] args){

        SeriableSample seriableSample = getInstance();
        System.out.println(seriableSample);

        seriableSample = SerializationUtils.clone(seriableSample);

        System.out.println(seriableSample);

    }
}
