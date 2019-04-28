package com.lesson.source.mybatis.custom;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lesson.source.mybatis.custom.entity.FlowRecord;
import com.lesson.source.mybatis.custom.mapper.FlowRecordMapper;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.IfSqlNode;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.scripting.xmltags.StaticTextSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.LongTypeHandler;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 *  {@link Configuration#addMappedStatement(org.apache.ibatis.mapping.MappedStatement)}
 *
 *
 *  {@link Configuration.StrictMap#put(java.lang.String, java.lang.Object)} 不能有相同的方法名 参数不同也不行
 * @author zhengshijun
 * @version created on 2019/4/11.
 */
public class CustomBootstrap {


	public static void main(String[] args) throws IOException {


		Configuration configuration = new Configuration();

		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setEnvironment(getEnvironment());


		List<SqlNode> sqlNodes = Lists.newArrayList();

		sqlNodes.add(new StaticTextSqlNode("select * from flow_record limit 1"));

		MixedSqlNode mixedSqlNode = new MixedSqlNode(sqlNodes);
		SqlSource dynamicSqlSource = new DynamicSqlSource(configuration, mixedSqlNode);

		String id = "com.lesson.source.mybatis.custom.mapper.FlowRecordMapper.selectOne";
		SqlSource sqlSource = dynamicSqlSource; //new StaticSqlSource(configuration, "select * from flow_record limit 1");
		List<ResultMap> resultMaps = new ArrayList<>();
		List<ResultMapping> resultMappings = new ArrayList<>();
		resultMappings.add(new ResultMapping.Builder(configuration, "userId", "user_id", new LongTypeHandler()).build());
		resultMappings.add(new ResultMapping.Builder(configuration, "orderId", "order_id", new LongTypeHandler()).build());
		ResultMap resultMap = new ResultMap.Builder(configuration, "sql", FlowRecord.class, resultMappings).build();
		resultMaps.add(resultMap);
		MappedStatement statement =
				new MappedStatement.Builder(configuration, id, sqlSource, SqlCommandType.SELECT)
						.resultSetType(ResultSetType.DEFAULT)
						.statementType(StatementType.PREPARED)
						.resultMaps(resultMaps)
						.build();

		//configuration.addMappedStatement(statement);

		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);


		SqlSession sqlSession = sqlSessionFactory.openSession();
		configuration.addMapper(FlowRecordMapper.class);
//		Object object = sqlSession.selectOne(id);
//
//		System.out.println(JSON.toJSONString(object));
		FlowRecordMapper flowRecordMapper = sqlSession.getMapper(FlowRecordMapper.class);
		System.out.println(JSON.toJSONString(flowRecordMapper.selectOne1()));


	}

	public static Environment getEnvironment() {
		return new Environment("dev", new JdbcTransactionFactory(), getDataSource());
	}

	private static DataSource getDataSource() {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser("root");
		dataSource.setPassword("xxxx");
		dataSource.setUrl("jdbc:mysql://10.0.20.4:3306/test1?useUnicode=true&useSSL=false&characterEncoding=utf8");
		return dataSource;
	}
}
