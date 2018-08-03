package com.chapter.cloud.eureka.api.model;

import com.alibaba.fastjson.JSON;

/**
 * @author zhengshijun
 * @version created on 2018/8/3.
 */
public class BaseEntry {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
