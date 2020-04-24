package com.lesson.source.pattern.chain;

/**
 * @author zhengshijun
 * @version created on 2020/4/24.
 */
public interface Invoker {

	/**
	 * 调用
	 * @param invocation 调用文件
	 * @return 返回结果
	 */
	Result invoke(Invocation invocation);
}
