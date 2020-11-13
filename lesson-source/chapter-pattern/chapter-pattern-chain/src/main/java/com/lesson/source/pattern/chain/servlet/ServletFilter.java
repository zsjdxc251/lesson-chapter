package com.lesson.source.pattern.chain.servlet;

/**
 * @author zhengshijun
 * @version created on 2020/4/24.
 */
public interface ServletFilter {

	/**
	 *  执行 filter
	 * @param request
	 * @param response
	 * @param filterChain 链条
	 */
	void doFilter(Request request,Response response,FilterChain filterChain);
}
