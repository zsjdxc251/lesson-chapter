package com.lesson.source.pattern.chain;

/**
 * @author zhengshijun
 * @version created on 2020/4/24.
 */
public class TimerFilter implements Filter{
	@Override
	public Result invoke(Invoker next, Invocation invocation) {

		long start = System.currentTimeMillis();
		System.out.println("开始计时");
		Result result = next.invoke(invocation);

		System.out.println("耗时ms："+(System.currentTimeMillis() - start));
		return result;
	}
}
