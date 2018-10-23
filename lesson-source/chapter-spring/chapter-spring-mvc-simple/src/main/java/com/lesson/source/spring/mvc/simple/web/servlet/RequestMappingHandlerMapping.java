package com.lesson.source.spring.mvc.simple.web.servlet;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/10/23.
 */
public class RequestMappingHandlerMapping implements HandlerMapping {

    private final Map<String, HandlerMethod> handlerMap = new LinkedHashMap<>();

    private final List<HandlerMethod> handlerMethods = new LinkedList<>();

    public void addHandlerMethod(HandlerMethod handlerMethod) {
        handlerMethods.add(handlerMethod);
    }

    @Override
    public HandlerMethod getHandler(HttpServletRequest request) {
        String uri = request.getRequestURI();
        for (HandlerMethod handlerMethod : handlerMethods ){
            if (handlerMethod.getUrlRex().matcher(uri).matches()){
                return handlerMethod;
            }
        }
        return null;
    }
}
