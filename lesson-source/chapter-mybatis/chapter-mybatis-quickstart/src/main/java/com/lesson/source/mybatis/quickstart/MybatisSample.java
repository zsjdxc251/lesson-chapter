package com.lesson.source.mybatis.quickstart;

import com.lesson.source.mybatis.quickstart.mapper.CityMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
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

        System.out.println(cityMapper.selectByPrimaryKey(726L));


        cityMapper = sqlSession.getMapper(CityMapper.class);

        System.out.println(cityMapper.selectByPrimaryKey(726L));




    }
}
