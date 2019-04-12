package com.lesson.source.mybatis.spring.configure;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import com.lesson.source.mybatis.spring.model.Activity;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.LongTypeHandler;
import org.apache.ibatis.type.StringTypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2018/10/9.
 */
@Configuration
@MapperScan("com.lesson.source.mybatis.spring.mapper")
public class MybatisConfigure {

	private static final Logger log = LoggerFactory.getLogger(MybatisConfigure.class);

	private static final String MAPPER_FOLDER = "mapper/*.xml";
	private static final String MYBATIS_CONFIG_FILE = "mybatis-config.xml";
	private static final String TYPE_ALIASES_PACKAGE = "com.lesson.source.mybatis.spring.model";

	@Value("${jdbc.url}")
	private String url;

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Value("${jdbc.driver}")
	private String driver;


	@Bean
	public DruidDataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setDriverClassName(driver);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}


	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		try {
			sqlSessionFactoryBean.setMapperLocations(
					new PathMatchingResourcePatternResolver().getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + MAPPER_FOLDER));

			sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(MYBATIS_CONFIG_FILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactoryBean.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = sqlSessionFactoryBean.getObject();

		} catch (Exception e) {
			e.printStackTrace();
		}


		afterConfiguration(sqlSessionFactory);
		return sqlSessionFactory;
	}

	private void afterConfiguration(SqlSessionFactory sqlSessionFactory) {

		org.apache.ibatis.session.Configuration configuration =
				sqlSessionFactory.getConfiguration();

		configuration.getMapperRegistry().getMappers();

		String id = "com.lesson.source.mybatis.spring.mapper.ActivityMapper.selectByPrimaryKey";
		List<ParameterMapping> mappings = Lists.newArrayList();
		mappings.add(new ParameterMapping.Builder(configuration, "id", Object.class).mode(ParameterMode.IN).build());

		SqlSource sqlSource = new StaticSqlSource(configuration, "select * from t_activity where `id` = ?", mappings);
		List<ResultMap> resultMaps = new ArrayList<>();
		List<ResultMapping> resultMappings = new ArrayList<>();
		resultMappings.add(new ResultMapping.Builder(configuration, "name", "name", new StringTypeHandler()).build());
		resultMappings.add(new ResultMapping.Builder(configuration, "url", "url", new StringTypeHandler()).build());
		resultMappings.add(new ResultMapping.Builder(configuration, "id", "id", new LongTypeHandler()).build());
		ResultMap resultMap = new ResultMap.Builder(configuration, "sql", Activity.class, resultMappings).build();
		resultMaps.add(resultMap);
		MappedStatement statement =
				new MappedStatement.Builder(configuration, id, sqlSource, SqlCommandType.SELECT)
						.resultSetType(ResultSetType.SCROLL_SENSITIVE)
						.statementType(StatementType.PREPARED)
						.resultMaps(resultMaps)
						// .parameterMap(new ParameterMap.Builder(configuration,"com.lesson.source.mybatis.spring.mapper.ActivityMapper.selectByPrimaryKey-Inline", null, null)).build())
						.build();

		configuration.addMappedStatement(statement);

	}


	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);

		return sqlSessionTemplate;
	}


}
