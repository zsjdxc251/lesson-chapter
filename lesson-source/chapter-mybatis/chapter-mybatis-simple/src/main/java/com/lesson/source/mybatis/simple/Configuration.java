package com.lesson.source.mybatis.simple;

/**
 * @author zhengshijun
 * @version created on 2018/10/13.
 */
public class Configuration {

    private MapperRegistry mapperRegistry = new MapperRegistry(this);

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> entry,SqlSession sqlSession) {

        return mapperRegistry.getMapper(entry,sqlSession);
    }
}
