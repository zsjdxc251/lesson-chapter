package com.lesson.source.mybatis.spring.configure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author zhengshijun
 * @version created on 2018/10/9.
 */
@Configuration
@EnableTransactionManagement
@PropertySource("application.properties")
@ComponentScan("com.lesson.source.mybatis.spring")
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass = true)
public class CoreConfigure {

    private static final Logger log = LoggerFactory.getLogger(CoreConfigure.class);

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
