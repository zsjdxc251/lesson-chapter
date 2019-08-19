package com.lesson.source.mybatis.plus.quickstart.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lesson.AbstractSpringBootTest;
import com.lesson.source.mybatis.plus.quickstart.entity.UserTraAndInnodb;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTraAndInnodbMapperTest extends AbstractSpringBootTest {

    @Autowired
    private UserTraAndInnodbMapper userTraAndInnodbMapper;

    @Test
    public void test2() {
        LambdaQueryWrapper<UserTraAndInnodb> lambdaQueryWrapper =
                new QueryWrapper<UserTraAndInnodb>().lambda().select(com.lesson.source.mybatis.plus.quickstart.entity.UserTraAndInnodb::getPassword).eq(UserTraAndInnodb::getUsername, "12345");
        userTraAndInnodbMapper.selectTraAndInnAll(lambdaQueryWrapper);

    }

}