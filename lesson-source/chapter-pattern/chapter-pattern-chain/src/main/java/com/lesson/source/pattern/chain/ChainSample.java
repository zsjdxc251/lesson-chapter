package com.lesson.source.pattern.chain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2020/4/24.
 */
public class ChainSample {

	public static void main(String[] args) {
		Invoker invoker = buildInvokerChain(new DubboInvoker());

		invoker.invoke(new Invocation() {
		});

	}

	public static Invoker buildInvokerChain(Invoker invoker) {
		Invoker last = invoker;
		List<Filter> filters = Lists.newArrayList();
		filters.add(new TimerFilter());

		for (int i = filters.size() - 1; i >= 0; i --) {
			Invoker next = last;
			Filter filter = filters.get(i);
			last = invocation -> filter.invoke(next,invocation);
		}
		return last;
	}
}
