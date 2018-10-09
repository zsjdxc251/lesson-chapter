package com.lesson.source.mybatis.spring;

import com.lesson.source.mybatis.spring.configure.CoreConfigure;
import com.lesson.source.mybatis.spring.mapper.CityMapper;
import com.lesson.source.mybatis.spring.model.City;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/10/9.
 */
public class MybatisSpringSample {

    public static void main(String[] args){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(CoreConfigure.class);


        CityMapper cityMapper = applicationContext.getBean(CityMapper.class);

        System.out.println(cityMapper.selectByPrimaryKey(1L).getName());

        City city = new City();

        city.setId(4L);
        city.setName("55");
        city.setState("66");
        cityMapper.insert(city);


    }
}
