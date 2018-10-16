package com.lesson;

import com.lesson.distributed.transaction.atomikos.configure.CoreConfigure;
import com.lesson.distributed.transaction.atomikos.mapper.master.CityMapper;
import com.lesson.distributed.transaction.atomikos.mapper.slave.UserInfoMapper;
import com.lesson.distributed.transaction.atomikos.service.CoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = CoreConfigure.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AppTest 
{
    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CoreService coreService;

    @Test
    public void test1(){
        cityMapper.selectOne();
        userInfoMapper.selectOne();
    }

    @Test
    public void save(){
        coreService.save();
    }

}
