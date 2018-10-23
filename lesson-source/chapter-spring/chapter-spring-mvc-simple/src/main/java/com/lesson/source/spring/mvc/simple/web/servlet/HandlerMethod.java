package com.lesson.source.spring.mvc.simple.web.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author zhengshijun
 * @version created on 2018/10/23.
 */
public class HandlerMethod {

    private Object bean;

    private Method method;


    private Pattern urlRex;

    public HandlerMethod(Pattern urlRex , Object bean, Method method) {
        this.urlRex = urlRex;
        this.bean = bean;
        this.method = method;
    }

    public Object getBean() {
        return bean;
    }

    public Method getMethod() {
        return method;
    }

    public Pattern getUrlRex() {
        return urlRex;
    }
}
