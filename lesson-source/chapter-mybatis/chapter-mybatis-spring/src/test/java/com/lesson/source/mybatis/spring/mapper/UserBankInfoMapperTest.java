package com.lesson.source.mybatis.spring.mapper;

import com.lesson.BaseAppTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class UserBankInfoMapperTest extends BaseAppTest {

	@Autowired
	private UserBankInfoMapper userBankInfoMapper;

	@Test
	public void test1(){

		UserBankInfo userBankInfo = userBankInfoMapper.selectByPrimaryKey(1L);


	}

}