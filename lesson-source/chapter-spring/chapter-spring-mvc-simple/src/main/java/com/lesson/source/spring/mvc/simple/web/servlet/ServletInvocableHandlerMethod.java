package com.lesson.source.spring.mvc.simple.web.servlet;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * @author zhengshijun
 * @version created on 2018/10/24.
 */
public class ServletInvocableHandlerMethod extends InvocableHandlerMethod {


    public ServletInvocableHandlerMethod(HandlerMethod handlerMethod) {
        super(handlerMethod.getUrlRex(), handlerMethod.getBean(), handlerMethod.getMethod());
    }



    public void invokeAndHandle(ServletWebRequest request)  throws Exception{

        Object returnValue = invokeForRequest(request);

    }
}
