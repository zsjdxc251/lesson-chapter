package com.lesson.source.mybatis.custom;

/**
 * @author zhengshijun
 * @version created on 2019/4/12.
 */
public class UserService  implements BaseService<Long>{

	@Override
	public Long insert(Long value){

		return 1L;
	}
}
