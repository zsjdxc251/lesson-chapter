package com.lesson;


import com.lesson.source.mybatis.simple.Configuration;
import com.lesson.source.mybatis.simple.Executor;
import com.lesson.source.mybatis.simple.SimpleExecutor;
import com.lesson.source.mybatis.simple.SqlSession;

public class App
{
    public static void main( String[] args )
    {


        Executor executor = new SimpleExecutor();
        Configuration configuration = new Configuration();
        SqlSession sqlSession = new SqlSession(executor,configuration);



    }
}
