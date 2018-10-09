package com.lesson.source.mybatis.spring;

import com.lesson.source.mybatis.spring.configure.MybatisConfigure;
import com.lesson.source.mybatis.spring.mapper.CityMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/10/9.
 */
public class MybatisSpringSample {

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MybatisConfigure.class);


        CityMapper cityMapper = applicationContext.getBean(CityMapper.class);

        System.out.println(cityMapper.selectByPrimaryKey(1L).getName());


    }
}
