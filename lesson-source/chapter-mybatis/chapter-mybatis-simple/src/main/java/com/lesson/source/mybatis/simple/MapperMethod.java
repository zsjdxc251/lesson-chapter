package com.lesson.source.mybatis.simple;

/**
 * @author zhengshijun
 * @version created on 2018/10/13.
 */
public class MapperMethod {

    private SqlCommand sqlCommand;



    public Object execute(SqlSession session, Object[] args){

        return session.selectOne(sqlCommand.getName(),args);
    }
}
