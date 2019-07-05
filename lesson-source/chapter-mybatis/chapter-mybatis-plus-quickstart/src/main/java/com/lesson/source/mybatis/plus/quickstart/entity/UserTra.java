package com.lesson.source.mybatis.plus.quickstart.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.Instant;

/**
 * @author zhengshijun
 * @version created on 2019/7/5.
 */
@Data
@TableName("t_user_tra")
public class UserTra {

	private Long id;

	private String username;

	private String password;

	private Instant modifyTime;

	private Integer age;
}
