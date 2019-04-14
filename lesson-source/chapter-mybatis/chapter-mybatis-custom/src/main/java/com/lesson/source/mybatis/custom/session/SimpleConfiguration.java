package com.lesson.source.mybatis.custom.session;

import com.lesson.source.mybatis.custom.binding.SimpleMapperRegistry;
import org.apache.ibatis.session.Configuration;

/**
 * @author zhengshijun
 * @version created on 2019/4/12.
 */
public class SimpleConfiguration extends Configuration {

	private final SimpleMapperRegistry mapperRegistry = new SimpleMapperRegistry(this);

	@Override
	public <T> void addMapper(Class<T> type) {

		mapperRegistry.addMapper(type);


	}
}
