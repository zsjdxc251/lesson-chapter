package com.lesson.distributed.transaction.atomikos.configure;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author zhengshijun
 * @version created on 2018/10/16.
 */

@Configuration
@MapperScan("com.lesson.distributed.transaction.atomikos.mapper.master")
public class MybatisConfigure {

    @Primary
    @Bean
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource) {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        try {
            sqlSessionFactoryBean.setMapperLocations(
                    new PathMatchingResourcePatternResolver().getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "mapper/master/*.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }

    @Bean
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        try {
            sqlSessionFactoryBean.setMapperLocations(
                    new PathMatchingResourcePatternResolver().getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "mapper/slave/*.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sqlSessionFactory;
    }


    @Bean
    public MapperScannerConfigurer masterMapperScanner(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.lesson.distributed.transaction.atomikos.mapper.master");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("masterSqlSessionFactory");
        return mapperScannerConfigurer;
    }
    @Bean
    public MapperScannerConfigurer slaveMapperScanner(){
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.lesson.distributed.transaction.atomikos.mapper.slave");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("slaveSqlSessionFactory");
        return mapperScannerConfigurer;
    }

}
