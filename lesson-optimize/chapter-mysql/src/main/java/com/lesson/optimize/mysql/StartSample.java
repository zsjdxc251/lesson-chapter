package com.lesson.optimize.mysql;

import com.alibaba.fastjson.JSON;
import com.lesson.optimize.mysql.entity.UserInnodb;
import com.lesson.optimize.mysql.repository.UserInnodbRepository;
import com.lesson.optimize.mysql.service.UserInnodbService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhengshijun
 * @version created on 2019/6/14.
 */
@Slf4j
@Component
public class StartSample implements ApplicationRunner {


    private ExecutorService executorService =  Executors.newFixedThreadPool(10);



    @Autowired
    private UserInnodbRepository userInnodbRepository;

    @Autowired
    private UserInnodbService userInnodbService;


    private final static int delta=0x9fa5-0x4e00+1;

    private static final Random ran=new Random();
    public static char getRandomHan() {
        return (char) (0x4e00 + ran.nextInt(delta));
    }

    public static String genString(int count) {
        char[] str = new char[count];

        for (int i=0;i<count;i++) {

            str[i] = getRandomHan();

        }

        return new String(str);

    }


    @Override
    public void run(ApplicationArguments args) throws Exception {







        for(int i=0;i<10;i++) {

            executorService.execute(()->{


                while (true) {


                    UserInnodb userInnodb =  UserInnodb
                            .builder()
                            .username(RandomStringUtils.randomAscii(10))
                            .phoneNumber(RandomUtils.nextLong(10000000000L,99999999999L))
                            .password(UUID.randomUUID().toString().replaceAll("-", StringUtils.EMPTY))
                            .age(RandomUtils.nextInt(10,100))
                            .email(RandomStringUtils.random(10).concat("@163.com"))
                            .gender((byte)(RandomUtils.nextBoolean()?1:2))
                            .address(genString(20)).build();



                    System.out.println(JSON.toJSONString(userInnodb,true));

                    Long id = userInnodbService.save(userInnodb);

                    System.out.println(id);
                }




            });


        }






    }
}
