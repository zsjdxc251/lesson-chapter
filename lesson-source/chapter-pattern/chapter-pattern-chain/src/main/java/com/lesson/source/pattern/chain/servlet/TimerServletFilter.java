package com.lesson.source.pattern.chain.servlet;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhengshijun
 * @version created on 2020/4/24.
 */
@Slf4j
public class TimerServletFilter implements ServletFilter{
	@Override
	public void doFilter(Request request, Response response, FilterChain filterChain) {

		log.info("开始计时");
		long start = System.currentTimeMillis();

		filterChain.doFilter(request,response);


		log.info("耗时："+(System.currentTimeMillis() - start));
	}
}
