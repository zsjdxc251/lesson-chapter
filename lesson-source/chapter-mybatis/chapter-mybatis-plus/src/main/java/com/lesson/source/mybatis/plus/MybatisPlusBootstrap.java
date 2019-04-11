package com.lesson.source.mybatis.plus;

import com.baomidou.mybatisplus.core.MybatisMapperAnnotationBuilder;
import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * {@link MybatisMapperAnnotationBuilder}
 * {@link com.baomidou.mybatisplus.core.injector.AbstractMethod#inject(org.apache.ibatis.builder.MapperBuilderAssistant, java.lang.Class, java.lang.Class, com.baomidou.mybatisplus.core.metadata.TableInfo)}
 * {@code com.baomidou.mybatisplus.core.injector.AbstractMethod#addMappedStatement(java.lang.Class, java.lang.String, org.apache.ibatis.mapping.SqlSource, org.apache.ibatis.mapping.SqlCommandType, java.lang.Class, java.lang.String, java.lang.Class, org.apache.ibatis.executor.keygen.KeyGenerator, java.lang.String, java.lang.String) }
 *
 *   {@link Configuration#addMappedStatement(org.apache.ibatis.mapping.MappedStatement)}
 * @author zhengshijun
 * @version created on 2019/2/27.
 */

@SpringBootApplication
public class MybatisPlusBootstrap {

    public static void main(String[] args){

        SpringApplication.run(MybatisPlusBootstrap.class,args);

    }
}
