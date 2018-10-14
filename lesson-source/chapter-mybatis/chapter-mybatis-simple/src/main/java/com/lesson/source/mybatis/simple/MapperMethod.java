package com.lesson.source.mybatis.simple;

/**
 * @author zhengshijun
 * @version created on 2018/10/13.
 */
public class MapperMethod {

    private String statement;

    public MapperMethod(String statement) {
        this.statement = statement;
    }

    public Object execute(SqlSession session, Object[] args){

        return session.selectOne(statement,args);
    }
}
