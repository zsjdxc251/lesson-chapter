package com.lesson.source.mybatis.spring.mapper;

import com.lesson.BaseAppTest;
import com.lesson.source.mybatis.spring.model.Activity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class ActivityMapperTest extends BaseAppTest {

	@Autowired
	private ActivityMapper activityMapper;


	@Test
	public void findAll(){

		List<Activity> list = activityMapper.findAll();

	}

	@Test
	public void findOne(){

		activityMapper.selectByPrimaryKey(1L);
	}
}