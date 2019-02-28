package com.lesson.source.mybatis.plus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhengshijun
 * @version created on 2019/2/27.
 */

@SpringBootApplication
public class MybatisPlusBootstrap {

    public static void main(String[] args){

        SpringApplication.run(MybatisPlusBootstrap.class,args);

    }
}
