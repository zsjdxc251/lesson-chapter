package com.lesson.source.spring.mvc.simple.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/10/23.
 */
public class RequestMappingHandlerAdapter implements HandlerAdapter {

    @Override
    public boolean supports(Object handler) {

        return handler instanceof HandlerMethod;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler) throws Exception {

        return invokeHandlerMethod(request,response,handler);
    }


    public ModelAndView invokeHandlerMethod(HttpServletRequest request,
                                            HttpServletResponse response, HandlerMethod handlerMethod) throws Exception{

        ServletInvocableHandlerMethod invocableHandlerMethod = createInvocableHandlerMethod(handlerMethod);


        ServletWebRequest webRequest = new ServletWebRequest(request, response);

        ModelAndViewContainer mavContainer = new ModelAndViewContainer();


        invocableHandlerMethod.invokeAndHandle(webRequest,mavContainer);

        return getModelAndView(mavContainer,  webRequest);
    }

    private ModelAndView getModelAndView(ModelAndViewContainer mavContainer, ServletWebRequest webRequest) {


        Map<String,Object> model = mavContainer.getRedirectModel();
        ModelAndView mav = new ModelAndView( model,mavContainer.getViewName(), mavContainer.getStatus());
        if (!mavContainer.isViewReference()) {
            mav.setView((View) mavContainer.getView());
        }
        return mav;
    }


    public ServletInvocableHandlerMethod createInvocableHandlerMethod(HandlerMethod handlerMethod){

        return new ServletInvocableHandlerMethod(handlerMethod);
    }
}
