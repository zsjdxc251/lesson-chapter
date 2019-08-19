package com.lesson.source.pattern.proxy.asm;

/**
 * @author zhengshijun
 * @version created on 2019/7/16.
 */
public class MyClassLoader extends ClassLoader{

    public MyClassLoader() {
        super(Thread.currentThread().getContextClassLoader());
    }

    public Class<?> defineClassForName(String name, byte[] data) {
        return this.defineClass(name, data, 0, data.length);
    }


}
