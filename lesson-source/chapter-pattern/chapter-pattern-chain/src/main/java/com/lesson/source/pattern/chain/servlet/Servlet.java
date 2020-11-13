package com.lesson.source.pattern.chain.servlet;

/**
 * @author zhengshijun
 * @version created on 2020/4/24.
 */
public interface Servlet {

	void service(Request request,Response response);
}
