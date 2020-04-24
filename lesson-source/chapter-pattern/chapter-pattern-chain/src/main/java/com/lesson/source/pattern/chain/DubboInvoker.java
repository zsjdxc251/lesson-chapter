package com.lesson.source.pattern.chain;

/**
 * @author zhengshijun
 * @version created on 2020/4/24.
 */
public class DubboInvoker implements Invoker{
	@Override
	public Result invoke(Invocation invocation) {

		System.out.println("调用dubbo");
		return new Result();
	}
}
