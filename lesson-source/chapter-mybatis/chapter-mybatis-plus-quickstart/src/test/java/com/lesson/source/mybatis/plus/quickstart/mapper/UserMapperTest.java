package com.lesson.source.mybatis.plus.quickstart.mapper;

import com.lesson.AbstractSpringBootTest;
import com.lesson.source.mybatis.plus.quickstart.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends AbstractSpringBootTest {

	@Autowired
	private UserMapper userMapper;

	@Test
	public void test1(){

		User user = userMapper.selectById(2);

		System.out.println(user);
	}

}