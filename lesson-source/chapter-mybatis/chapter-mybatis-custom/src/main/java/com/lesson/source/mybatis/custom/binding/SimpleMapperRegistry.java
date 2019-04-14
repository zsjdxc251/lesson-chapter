package com.lesson.source.mybatis.custom.binding;

import com.lesson.source.mybatis.custom.annotation.SimpleMapperAnnotationBuilder;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.binding.MapperProxyFactory;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2019/4/12.
 */
public class SimpleMapperRegistry extends MapperRegistry {

	private Configuration config;
	private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

	public SimpleMapperRegistry(Configuration config) {

		super(config);
		this.config = config;
	}


	@Override
	public <T> void addMapper(Class<T> type) {

		if (type.isInterface()) {
			if (hasMapper(type)) {
				throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
			}
			boolean loadCompleted = false;
			try {

				knownMappers.put(type, new MapperProxyFactory<>(type));
				// It's important that the type is added before the parser is run
				// otherwise the binding may automatically be attempted by the
				// mapper parser. If the type is already known, it won't try.
				SimpleMapperAnnotationBuilder parser = new SimpleMapperAnnotationBuilder(config, type);
				parser.parse();
				loadCompleted = true;
			} finally {
				if (!loadCompleted) {
					knownMappers.remove(type);
				}
			}
		}
	}
}
