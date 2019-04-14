package com.lesson.source.mybatis.custom;

/**
 * @author zhengshijun
 * @version created on 2019/4/12.
 */
public interface BaseService<P> {

	P insert(P v);
}
