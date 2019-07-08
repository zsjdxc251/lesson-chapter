package com.lesson.source.mybatis.dynamic.sql.mapper;

import com.lesson.source.mybatis.dynamic.sql.model.SimpleTableRecord;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.DateTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2019/7/8.
 */
public interface SimpleTableAnnotatedMapper {

	@InsertProvider(type = SqlProviderAdapter.class, method = "insert")
	int insert(InsertStatementProvider<SimpleTableRecord> insertStatement);

	@UpdateProvider(type = SqlProviderAdapter.class, method = "update")
	int update(UpdateStatementProvider updateStatement);

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@Results(id = "SimpleTableResult", value = {
			@Result(column = "A_ID", property = "id", jdbcType = JdbcType.INTEGER, id = true),
			@Result(column = "first_name", property = "firstName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "last_name", property = "lastName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "birth_date", property = "birthDate", jdbcType = JdbcType.DATE),
			@Result(column = "employed", property = "employed", jdbcType = JdbcType.VARCHAR, typeHandler = DateTypeHandler.class),
			@Result(column = "occupation", property = "occupation", jdbcType = JdbcType.VARCHAR)
	})
	List<SimpleTableRecord> selectMany(SelectStatementProvider selectStatement);


	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("SimpleTableResult")
	SimpleTableRecord selectOne(SelectStatementProvider selectStatement);

	@DeleteProvider(type = SqlProviderAdapter.class, method = "delete")
	int delete(DeleteStatementProvider deleteStatement);

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	long count(SelectStatementProvider selectStatement);

}
