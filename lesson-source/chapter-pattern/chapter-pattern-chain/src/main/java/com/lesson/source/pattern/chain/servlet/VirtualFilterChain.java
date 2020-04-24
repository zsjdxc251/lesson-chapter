package com.lesson.source.pattern.chain.servlet;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2020/4/24.
 */
@Slf4j
public class VirtualFilterChain implements FilterChain{

	private List<ServletFilter> filters = Lists.newArrayList();
	 {
		 filters.add(new TimerServletFilter());

		 filters.add(new TimerServletFilter());

	}

	private int index = 0 ;

	@Override
	public void doFilter(Request request, Response response) {


		if (index>=filters.size()){

			new Servlet(){

				@Override
				public void service(Request request, Response response) {

					log.info("开始执行servlet");

				}
			}.service(request,response);
			return;
		}

		log.info("开始 VirtualFilterChain");
		index ++;
		ServletFilter filter = filters.get(index-1);

		filter.doFilter(request,response,this);



		log.info("结束 VirtualFilterChain");

	}
}
