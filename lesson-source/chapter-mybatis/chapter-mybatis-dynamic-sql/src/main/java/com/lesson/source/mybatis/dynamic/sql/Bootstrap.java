package com.lesson.source.mybatis.dynamic.sql;

import com.lesson.source.mybatis.dynamic.sql.mapper.SimpleTableAnnotatedMapper;
import com.lesson.source.mybatis.dynamic.sql.model.SimpleTableRecord;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategy;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import static org.mybatis.dynamic.sql.SqlBuilder.isNull;
import static org.mybatis.dynamic.sql.SqlBuilder.select;

/**
 *
 *  http://www.mybatis.org/mybatis-dynamic-sql/docs/quickStart.html
 *
 * @author zhengshijun
 * @version created on 2019/7/8.
 */
public class Bootstrap {

	public static void main(String[] args) throws FileNotFoundException {
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(new FileInputStream(""));
		try (SqlSession session = sqlSessionFactory.openSession()) {
			SimpleTableAnnotatedMapper mapper = session.getMapper(SimpleTableAnnotatedMapper.class);

			SelectStatementProvider selectStatement = select(SimpleTableDynamicSqlSupport.id.as("A_ID"))
					.from(SimpleTableDynamicSqlSupport.simpleTable)
					.where(SimpleTableDynamicSqlSupport.id, SqlBuilder.isEqualTo(1))
					.or(SimpleTableDynamicSqlSupport.occupation, isNull())
					.orderBy(SimpleTableDynamicSqlSupport.id)
					.build()
					.render(RenderingStrategy.MYBATIS3);

			List<SimpleTableRecord> rows = mapper.selectMany(selectStatement);


		}
	}
}
