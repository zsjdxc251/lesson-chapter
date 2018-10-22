package com.lesson.source.spring.mvc.simple.web.servlet;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/10/23.
 */
public class ModelAndView {

    private Map<String,Object> model;

    private String viewName;


    @Nullable
    protected Map<String, Object> getModelInternal() {
        return this.model;
    }

    public String getViewName() {
        return viewName;
    }
}
