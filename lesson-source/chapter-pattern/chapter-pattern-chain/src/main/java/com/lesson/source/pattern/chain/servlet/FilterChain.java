package com.lesson.source.pattern.chain.servlet;

/**
 * @author zhengshijun
 * @version created on 2020/4/24.
 */
public interface FilterChain {

	/**
	 *  执行 filter
	 * @param request
	 * @param response
	 */
	void doFilter(Request request,Response response);
}
