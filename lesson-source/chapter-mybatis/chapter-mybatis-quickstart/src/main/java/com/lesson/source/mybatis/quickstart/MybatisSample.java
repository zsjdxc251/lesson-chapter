package com.lesson.source.mybatis.quickstart;

import com.lesson.source.mybatis.quickstart.mapper.CityMapper;
import com.lesson.source.mybatis.quickstart.model.City;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.executor.BaseExecutor;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSession;

import java.io.IOException;
import java.io.InputStream;


/**
 *
 *
 *
 *
 *
 *   {@link CityMapper#selectByPrimaryKey(java.lang.Long)}
 *   = >>
 *   {@link MapperProxy#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])}
 *   =>>
 *   {@link MapperMethod#execute(org.apache.ibatis.session.SqlSession, java.lang.Object[])}
 *
 *   =>>
 *   {@link DefaultSqlSession#selectOne(java.lang.String, java.lang.Object)}
 *
 *    {@link DefaultSqlSession#selectList(java.lang.String, java.lang.Object, org.apache.ibatis.session.RowBounds)}
 *        方法中
 *   =>>
 *   {@link CachingExecutor#query(org.apache.ibatis.mapping.MappedStatement, java.lang.Object, org.apache.ibatis.session.RowBounds, org.apache.ibatis.session.ResultHandler)}
 *   =>>
 *   {@link BaseExecutor#query(org.apache.ibatis.mapping.MappedStatement, java.lang.Object, org.apache.ibatis.session.RowBounds, org.apache.ibatis.session.ResultHandler, org.apache.ibatis.cache.CacheKey, org.apache.ibatis.mapping.BoundSql)}
 *
 *   =>>
 *   {@link SimpleExecutor#doQuery(org.apache.ibatis.mapping.MappedStatement, java.lang.Object, org.apache.ibatis.session.RowBounds, org.apache.ibatis.session.ResultHandler, org.apache.ibatis.mapping.BoundSql)}
 *
 *
 *   =>>
 *   {@link RoutingStatementHandler#query(java.sql.Statement, org.apache.ibatis.session.ResultHandler)}
 *
 *   =>>
 *   {@link PreparedStatementHandler#query(java.sql.Statement, org.apache.ibatis.session.ResultHandler)}
 *
 *    结果
 *
 *   =>>
 *   {@link DefaultResultSetHandler#handleResultSets(java.sql.Statement)}
 *
 *
 * @author zhengshijun
 * @version created on 2018/10/9.
 */
public class MybatisSample {


    public static void main(String[] args) throws IOException{

        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);


        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        SqlSession sqlSession = sqlSessionFactory.openSession();

        CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);

        City city =  cityMapper.selectByPrimaryKey(726L);

       // Country   country = cityMapper.selectCountry(13);




    }
}
