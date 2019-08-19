package com.lesson.source.mybatis.plus.quickstart.mapper;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.lesson.AbstractSpringBootTest;
import com.lesson.source.mybatis.plus.quickstart.entity.UserTra;
import com.lesson.source.mybatis.plus.quickstart.entity.UserTraAndInnodb;
import com.lesson.source.mybatis.plus.quickstart.entity.UserTraAndInnodbTwo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTraMapperTest extends AbstractSpringBootTest {

	@Autowired
	private UserTraMapper userTraMapper;

	@Test
	public void test1() {

		LambdaQueryWrapper<UserTra> lambdaQueryWrapper =
				new QueryWrapper<UserTra>().lambda()
						.select(UserTra::getUsername, UserTra::getPassword, UserTra::getModifyTime, UserTra::getAge)
				   .eq(UserTra::getAge,2);


//		lambdaQueryWrapper.orderByAsc(UserTra::getAge);
//


		lambdaQueryWrapper.orderByAsc(UserTra::getAge);

		//lambdaQueryWrapper.getExpression().getOrderBy().add(SqlKeyword.ORDER_BY);
		lambdaQueryWrapper.getExpression().getOrderBy().add(new ISqlSegment() {
			@Override
			public String getSqlSegment() {
				return ",age";
			}
		});
		lambdaQueryWrapper.getExpression().getOrderBy().add(SqlKeyword.IS_NOT_NULL);


		System.out.println(lambdaQueryWrapper.getSqlSet());
		System.out.println(lambdaQueryWrapper.getSqlSelect());
		System.out.println(lambdaQueryWrapper.getEntity());
		System.out.println(lambdaQueryWrapper.getCustomSqlSegment());
		System.out.println(lambdaQueryWrapper.getParamNameValuePairs());
		System.out.println(lambdaQueryWrapper.getParamAlias());
		System.out.println(lambdaQueryWrapper.getSqlSegment());

		System.out.println(lambdaQueryWrapper.getSqlSet());
		userTraMapper.selectList(lambdaQueryWrapper);

	}

	@Test
	public void test2() {

		TableInfoHelper.initTableInfo(null,UserTraAndInnodbTwo.class);
		LambdaQueryWrapper<UserTraAndInnodbTwo> lambdaQueryWrapper =
				new QueryWrapper<UserTraAndInnodbTwo>().lambda().select(UserTraAndInnodbTwo::getPassword).eq(UserTraAndInnodbTwo::getUsername, "12345");
		userTraMapper.selectTraAndInnAll(lambdaQueryWrapper);

		System.out.println(JSON.toJSONString(lambdaQueryWrapper,true));

	}

	@Test
	public void test3(){
		TableInfoHelper.initTableInfo(null,UserTraAndInnodbTwo.class);
		TableInfo tableInfoHelper = TableInfoHelper.getTableInfo(UserTraAndInnodbTwo.class);

		System.out.println(JSON.toJSONString(tableInfoHelper,true));
	}




}