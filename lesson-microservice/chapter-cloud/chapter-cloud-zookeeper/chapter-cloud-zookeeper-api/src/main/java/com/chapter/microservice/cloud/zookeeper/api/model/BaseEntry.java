package com.chapter.microservice.cloud.zookeeper.api.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author zhengshijun
 * @version created on 2018/8/6.
 */
public abstract class BaseEntry implements Serializable {
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
