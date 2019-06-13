package com.lesson.source.mybatis.plus.quickstart.service;

import com.lesson.AbstractSpringBootTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends AbstractSpringBootTest {

	@Autowired
	private UserService userService;

	@Test
	public void getByPhoneNumber() {

		System.out.println(userService.getByPhoneNumber("080123456789"));
	}
}