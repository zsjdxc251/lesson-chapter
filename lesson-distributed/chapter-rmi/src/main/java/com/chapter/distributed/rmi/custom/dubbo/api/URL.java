package com.chapter.distributed.rmi.custom.dubbo.api;

/**
 * @author zhengshijun
 * @version created on 2018/7/3.
 */
public class URL {
    private final String name;

    public URL() {
        this.name = null;
    }

    public URL(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
