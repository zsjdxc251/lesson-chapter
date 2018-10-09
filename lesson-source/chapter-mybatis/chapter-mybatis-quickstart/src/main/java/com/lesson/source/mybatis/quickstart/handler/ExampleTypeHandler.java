package com.lesson.source.mybatis.quickstart.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author zhengshijun
 * @version created on 2018/10/9.
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ExampleTypeHandler  extends BaseTypeHandler<String> {

    private static final Logger log = LoggerFactory.getLogger(ExampleTypeHandler.class);

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        log.info("parameter:{},jdbcType:{}",parameter,jdbcType);
        ps.setString(i, parameter);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        log.info("columnName:{}",columnName);
        return rs.getString(columnName);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        log.info("columnIndex:{}",columnIndex);
        return rs.getString(columnIndex);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        log.info("columnIndex:{}",columnIndex);
        return cs.getString(columnIndex);
    }
}
