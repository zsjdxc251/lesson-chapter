package com.lesson.source.pattern.singleton.register;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class RegisterSample {

    ConcurrentMap<String,Object> concurrentMap = new ConcurrentHashMap<>(10);
}
