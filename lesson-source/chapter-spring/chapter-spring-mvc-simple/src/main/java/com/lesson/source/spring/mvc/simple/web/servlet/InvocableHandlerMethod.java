package com.lesson.source.spring.mvc.simple.web.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author zhengshijun
 * @version created on 2018/10/24.
 */
public class InvocableHandlerMethod extends HandlerMethod {


    public InvocableHandlerMethod(Pattern urlRex, Object bean, Method method) {
        super(urlRex, bean, method);
    }



    public Object invokeForRequest(ServletWebRequest servletWebRequest) throws Exception{
        Object[] args =getMethodArgumentValues(servletWebRequest);

        return doInvoke(args);
    }


    private Object[] getMethodArgumentValues(ServletWebRequest request) {


        return null;
    }

    public Object doInvoke(Object... args) throws Exception{



        try {
            return getMethod().invoke(getBean(),args);
        } catch (Exception e) {
            throw new Exception(e);
        }

    }
}
