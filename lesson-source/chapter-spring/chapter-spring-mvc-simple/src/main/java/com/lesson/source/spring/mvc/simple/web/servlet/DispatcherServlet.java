package com.lesson.source.spring.mvc.simple.web.servlet;

import javax.annotation.Nullable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/10/23.
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        doDispatch(req,resp);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mv = null;
        
        HandlerMethod handlerMethod = getHandler(req);



        HandlerAdapter handlerAdapter = getHandlerAdapter(handlerMethod);

        Exception dispatchException = null;

        try {
            
            mv = handlerAdapter.handle(req,resp,handlerMethod);

        } catch (Exception e) {
            dispatchException = e;
        }

        processDispatchResult(req, resp, handlerMethod, mv, dispatchException);

    }

    private void processDispatchResult(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, ModelAndView mv, Exception dispatchException) {
        boolean errorView = false;
        if (mv != null ) {
            render(mv, request, response);

        }

    }

    private void render(ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {

        String viewName = mv.getViewName();

        View view = resolveViewName(viewName,mv.getModelInternal(),request);


        try {
            view.render(mv.getModelInternal(),request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private View resolveViewName(String viewName, @Nullable Map<String, Object> model,
                                 HttpServletRequest request){

        return null;

    }

    private HandlerAdapter getHandlerAdapter(HandlerMethod handlerMethod) {

        return null;
    }

    private HandlerMethod getHandler(HttpServletRequest req) {

        return null;
    }
    
    


}
