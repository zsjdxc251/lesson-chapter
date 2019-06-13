package com.lesson.source.mybatis.plus.quickstart.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.lesson.source.mybatis.plus.quickstart.mapper.UserMapper;
import lombok.Data;

/**
 * @author zhengshijun
 * @version created on 2019/6/13.
 */
@Data
@TableName("t_user")
public class User extends Model<User> {

	@TableId(type = IdType.AUTO)
	private Long uid;

	private Integer role;

	private String phoneNumber;


	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
