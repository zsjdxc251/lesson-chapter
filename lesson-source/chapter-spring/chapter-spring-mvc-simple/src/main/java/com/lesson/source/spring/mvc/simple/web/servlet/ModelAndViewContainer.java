package com.lesson.source.spring.mvc.simple.web.servlet;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/10/24.
 */
public class ModelAndViewContainer {
    @Nullable
    private Object view;

    @Nullable
    private Map<String,Object> redirectModel;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Nullable
    public Object getView() {
        return view;
    }

    public void setView(@Nullable Object view) {
        this.view = view;
    }

    @Nullable
    public Map<String, Object> getRedirectModel() {
        return redirectModel;
    }

    public void setRedirectModel(@Nullable Map<String, Object> redirectModel) {
        this.redirectModel = redirectModel;
    }

    @Nullable
    public String getViewName() {
        return (this.view instanceof String ? (String) this.view : null);
    }

    public boolean isViewReference() {
        return (this.view instanceof String);
    }
}
