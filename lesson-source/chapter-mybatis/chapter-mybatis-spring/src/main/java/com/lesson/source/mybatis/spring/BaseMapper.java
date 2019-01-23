package com.lesson.source.mybatis.spring;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2019/1/22.
 */
public interface BaseMapper <T, E, PK extends Serializable>{
	long countByExample(E example);

	int deleteByExample(E example);

	int deleteByPrimaryKey(PK id);

	int insert(T record);

	int insertSelective(T record);

	List<T> selectByExample(E example);

	T selectByPrimaryKey(PK id);

	int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

	int updateByExample(@Param("record") T record, @Param("example") E example);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
}
