package com.lesson.source.mybatis.simple;

import java.lang.reflect.Proxy;

/**
 * @author zhengshijun
 * @version created on 2018/10/13.
 */
public class MapperRegistry {

    public MapperRegistry(Configuration configuration) {

    }


    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> entry,SqlSession sqlSession) {

        return (T) Proxy.newProxyInstance(entry.getClassLoader(),new Class<?>[]{entry},new MapperProxy(sqlSession));
    }

}
