package com.lesson.source.mybatis.plus.quickstart.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lesson.source.mybatis.plus.quickstart.entity.User;
import com.lesson.source.mybatis.plus.quickstart.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author zhengshijun
 * @version created on 2019/6/13.
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> {


	public User getByPhoneNumber(String phoneNumber){


		LambdaQueryWrapper<User> lambdaQueryWrapper =
				new QueryWrapper<User>().lambda().select(User::getPhoneNumber,User::getUid).eq(User::getPhoneNumber,phoneNumber);


		new UpdateWrapper<User>().lambda().set(User::getPhoneNumber,2).eq(User::getUid,2);

		return baseMapper.selectOne(lambdaQueryWrapper);
	}

}
