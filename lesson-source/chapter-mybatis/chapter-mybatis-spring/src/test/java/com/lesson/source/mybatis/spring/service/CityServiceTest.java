package com.lesson.source.mybatis.spring.service;

import com.lesson.BaseAppTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CityServiceTest extends BaseAppTest {

    @Autowired
    private CityService cityService;

    @Test
    public void save() {

        System.out.println(cityService.getClass());
        cityService.save("22","11");
    }

    @Test
    public void find(){

        cityService.findAll();

    }
}