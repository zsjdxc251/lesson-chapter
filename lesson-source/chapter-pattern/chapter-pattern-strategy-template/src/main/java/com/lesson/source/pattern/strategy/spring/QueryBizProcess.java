package com.lesson.source.pattern.strategy.spring;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2019/6/3.
 */
public class QueryBizProcess {

	private final List<QueryProcess> processes = ImmutableList.of(new QueryAProcess(), new QueryBProcess());

	public void query(Object params, Class<?> clazz) {


		processes
				.stream()
				.filter(value -> value.supports(clazz))
				.forEach(value -> {

					value.handle(params);

				});


	}


}
