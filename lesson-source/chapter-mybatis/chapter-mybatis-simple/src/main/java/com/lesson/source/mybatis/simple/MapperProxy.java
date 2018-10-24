package com.lesson.source.mybatis.simple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author zhengshijun
 * @version created on 2018/10/13.
 */
public class MapperProxy implements InvocationHandler {


    private final SqlSession sqlSession;

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Class<?> targetClass = method.getDeclaringClass();

        MapperMethod mapperMethod = null;

        return mapperMethod.execute(sqlSession,args);
    }
}
