package com.lesson.source.pattern.chain.servlet;

/**
 * @author zhengshijun
 * @version created on 2020/4/24.
 */
public class ChainSample {

	public static void main(String[] args) {



		new VirtualFilterChain().doFilter(new Request(),new Response());




	}
}
