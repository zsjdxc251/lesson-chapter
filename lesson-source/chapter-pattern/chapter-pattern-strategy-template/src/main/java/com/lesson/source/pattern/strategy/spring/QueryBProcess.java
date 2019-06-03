package com.lesson.source.pattern.strategy.spring;

/**
 * @author zhengshijun
 * @version created on 2019/6/3.
 */
public class QueryBProcess implements QueryProcess {
	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void handle(Object params) {

	}
}
