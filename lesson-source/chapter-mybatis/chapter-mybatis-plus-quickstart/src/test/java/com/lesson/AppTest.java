package com.lesson;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.SerializationUtils;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.core.toolkit.support.SerializedLambda;
import com.lesson.source.mybatis.plus.quickstart.entity.User;
import com.lesson.source.mybatis.plus.quickstart.entity.UserTra;
import com.lesson.source.mybatis.plus.quickstart.entity.UserTraAndInnodbTwo;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

/**
 * Unit test for simple App.
 */
public class AppTest {
	/**
	 * Rigorous Test :-)
	 */
	@Test
	public void shouldAnswerWithTrue() {




		SerializedLambda serializedLambda = LambdaUtils.resolve(User::getPhoneNumber);

		System.out.println(serializedLambda.getImplMethodName());


	}

	public <T> void test1(SFunction<T, ?> lambda) {
		try (ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(SerializationUtils.serialize(lambda))) {
			@Override
			protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws IOException, ClassNotFoundException {
				Class<?> clazz = super.resolveClass(objectStreamClass);
				return clazz == java.lang.invoke.SerializedLambda.class ? SerializedLambda.class : clazz;
			}
		}) {

			SerializedLambda serializedLambda = (SerializedLambda)objIn.readObject();


			System.out.println(serializedLambda.getImplMethodName());
		} catch (Exception e) {
			throw ExceptionUtils.mpe("This is impossible to happen", e);
		}
	}


	@Test
	public void test1(){

		TableInfoHelper.initTableInfo(null, UserTra.class);
		LambdaQueryWrapper<UserTra> lambdaQueryWrapper =
				new QueryWrapper<UserTra>().lambda()
						.select(UserTra::getUsername, UserTra::getPassword, UserTra::getModifyTime, UserTra::getAge)
						.eq(UserTra::getAge,2).eq(UserTra::getModifyTime,"232132");
		System.out.println(JSON.toJSONString(lambdaQueryWrapper.getExpression()));
		System.out.println(JSON.toJSONString(lambdaQueryWrapper,true));
	}

}
