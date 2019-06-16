package com.lesson.source.mybatis.plus.quickstart.service;

import com.lesson.AbstractSpringBootTest;
import com.lesson.source.mybatis.plus.quickstart.entity.UserInnodb;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class UserInnodbServiceTest extends AbstractSpringBootTest {

    @Autowired
    private UserInnodbService userInnodbService;


    @Test
    public void save() throws InterruptedException {

       for (int i=0;i<10;i++) {


           new Thread(()->{
               while (true) {
                   UserInnodb userInnodb =  UserInnodb
                           .builder()
                           .username(RandomStringUtils.randomAscii(10))
                           .phoneNumber(RandomUtils.nextLong(10000000000L,99999999999L))
                           .password(UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY))
                           .age(RandomUtils.nextInt(10,100))
                           .email(RandomStringUtils.randomAlphabetic(8).concat("@163.com"))
                           .gender((byte)(RandomUtils.nextBoolean()?1:2))
                           .address("深圳").build();


                   userInnodbService.save(userInnodb);
               }
           }).start();
       }



        Thread.currentThread().join();
    }

    @Test
    public void test1() throws InterruptedException {
        save();
    }
}