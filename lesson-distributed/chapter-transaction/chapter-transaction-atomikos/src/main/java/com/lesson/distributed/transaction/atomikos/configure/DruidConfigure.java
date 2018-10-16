package com.lesson.distributed.transaction.atomikos.configure;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;

/**
 * @author zhengshijun
 * @version created on 2018/10/16.
 */
public class DruidConfigure {
    @Bean
    public DruidDataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?useSSL=true");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("zsj12345");
        return dataSource;
    }

    @Bean
    public DruidDataSource slaveDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/weway?useSSL=true");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("zsj12345");
        return dataSource;
    }

}
