package com.lesson.source.mybatis.simple;

import java.lang.reflect.Proxy;

/**
 * @author zhengshijun
 * @version created on 2018/10/13.
 */
public class Configuration {

    private MapperRegistry mapperRegistry;


    public Configuration(MapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> entry,SqlSession sqlSession) {

        return mapperRegistry.getMapper(entry,sqlSession);
    }
}
