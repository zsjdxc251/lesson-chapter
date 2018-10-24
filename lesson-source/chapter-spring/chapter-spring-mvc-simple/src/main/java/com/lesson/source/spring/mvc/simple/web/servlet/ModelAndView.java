package com.lesson.source.spring.mvc.simple.web.servlet;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/10/23.
 */
public class ModelAndView {

    private Map<String,Object> model;

    private Object view;

    private int status;

    public ModelAndView() {
    }

    public ModelAndView(Map<String, Object> model, Object view, int status) {
        this.model = model;
        this.view = view;
        this.status = status;
    }

    @Nullable
    protected Map<String, Object> getModelInternal() {
        return this.model;
    }


    public ModelAndView(String viewName) {
        this.view = viewName;
    }

    public ModelAndView(View view) {
        this.view = view;
    }

    @Nullable
    public String getViewName() {
        return (this.view instanceof String ? (String) this.view : null);
    }

    @Nullable
    public View getView() {
        return (this.view instanceof View ? (View) this.view : null);
    }


    public void setView(View view) {
        this.view = view;
    }
}
