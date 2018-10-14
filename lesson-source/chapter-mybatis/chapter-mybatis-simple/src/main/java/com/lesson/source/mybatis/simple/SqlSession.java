package com.lesson.source.mybatis.simple;

/**
 * @author zhengshijun
 * @version created on 2018/10/13.
 */
public class SqlSession {

    private final Executor executor;

    private final Configuration configuration;

    public SqlSession(Executor executor, Configuration configuration) {
        this.executor = executor;
        this.configuration = configuration;
    }


    public <T> T getMapper(Class<T> entry) {

        return configuration.getMapper(entry,this);
    }

    public Object selectOne(String statement,Object[] params) {

        return executor.query(statement,params);
    }
}
