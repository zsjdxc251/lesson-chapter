package com.lesson.source.mybatis.spring.commons;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * @author zhengshijun
 * @version created on 2019/4/12.
 */
public interface BaseMapper<T> {

	/**
	 *  主键查询
	 * @param key
	 * @return
	 */
	T selectByPrimaryKey(@Param("id") Serializable key);

}
