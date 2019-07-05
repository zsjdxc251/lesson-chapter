package com.lesson.source.mybatis.plus.quickstart.mapper;

import com.baomidou.mybatisplus.core.conditions.ISqlSegment;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlKeyword;
import com.lesson.AbstractSpringBootTest;
import com.lesson.source.mybatis.plus.quickstart.entity.UserTra;
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

}