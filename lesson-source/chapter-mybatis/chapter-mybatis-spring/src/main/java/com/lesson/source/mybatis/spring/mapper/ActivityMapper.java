package com.lesson.source.mybatis.spring.mapper;

import com.lesson.source.mybatis.spring.commons.BaseMapper;
import com.lesson.source.mybatis.spring.model.Activity;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2019/4/12.
 */
public interface ActivityMapper extends BaseMapper {


	/**
	 * 查询所有
	 * @return
	 */
	List<Activity> findAll();
}
