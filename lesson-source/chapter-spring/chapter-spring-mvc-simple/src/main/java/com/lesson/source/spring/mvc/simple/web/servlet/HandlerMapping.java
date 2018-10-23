package com.lesson.source.spring.mvc.simple.web.servlet;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhengshijun
 * @version created on 2018/10/23.
 */
public interface HandlerMapping {


    HandlerMethod getHandler(HttpServletRequest request);


}
