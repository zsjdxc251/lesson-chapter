package com.lesson.source.pattern.strategy.spring;

/**
 * @author zhengshijun
 * @version created on 2019/6/3.
 */
public interface QueryProcess {

	/**
	 *  是否支持
	 * @return
	 */
	boolean supports(Class<?> clazz);

	/**
	 *
	 * @param params
	 */
	void handle(Object params);


}
