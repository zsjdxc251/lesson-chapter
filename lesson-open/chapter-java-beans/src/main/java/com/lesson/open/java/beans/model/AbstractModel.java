package com.lesson.open.java.beans.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author zhengshijun
 * @version created on 2018/8/27.
 */
public abstract class AbstractModel implements Serializable {

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
