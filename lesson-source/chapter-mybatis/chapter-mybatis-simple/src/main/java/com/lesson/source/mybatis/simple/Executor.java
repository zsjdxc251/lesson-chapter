package com.lesson.source.mybatis.simple;

/**
 * @author zhengshijun
 * @version created on 2018/10/13.
 */
public interface Executor {


    Object query(String statement,Object[] params);
}
