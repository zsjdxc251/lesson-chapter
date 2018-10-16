package com.lesson.distributed.transaction.atomikos.service;

import com.lesson.distributed.transaction.atomikos.entity.City;
import com.lesson.distributed.transaction.atomikos.entity.UserInfo;
import com.lesson.distributed.transaction.atomikos.mapper.master.CityMapper;
import com.lesson.distributed.transaction.atomikos.mapper.slave.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhengshijun
 * @version created on 2018/10/16.
 */
@Service
public class CoreService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private CityMapper cityMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    public void save(){

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("123456");
        userInfo.setPassword("123456");
        userInfoMapper.insert(userInfo);



        City city = new City();
        city.setName("合并");
        city.setState("-1"+(0/1));
        cityMapper.insert(city);


    }
}
