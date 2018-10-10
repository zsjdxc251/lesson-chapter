package com.lesson.source.mybatis.spring.service;

import com.lesson.source.mybatis.spring.mapper.CityMapper;
import com.lesson.source.mybatis.spring.model.City;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhengshijun
 * @version created on 2018/10/10.
 */
@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = RuntimeException.class)
    public int save(String name , String state){

        City city = new City();
        city.setName(name);
        city.setState(state);
        int result = cityMapper.insert(city);

        ((CityService)AopContext.currentProxy()).save2(name,state);

        return result;

    }
    @Transactional(propagation = Propagation.NOT_SUPPORTED,rollbackFor = RuntimeException.class)
    public int save2(String name , String state){

        City city = new City();
        city.setName(name);
        city.setState(state);
        int result = cityMapper.insert(city);
        return result;

    }


    public City findOne(Long i) {

        City city =  cityMapper.selectByPrimaryKey(i);
        System.out.println(city);

        city =  cityMapper.selectByPrimaryKey(i);
        System.out.println(city);

        return city;


    }




}
