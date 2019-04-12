package com.lesson.source.mybatis.custom.annotation;

import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.session.Configuration;

/**
 * @author zhengshijun
 * @version created on 2019/4/12.
 */
public class SimpleMapperAnnotationBuilder extends MapperAnnotationBuilder {

	private Configuration configuration;

	private Class<?> type;

	public SimpleMapperAnnotationBuilder(Configuration configuration, Class<?> type) {
		super(configuration, type);
		this.configuration = configuration;
		this.type = type;
	}


}
