package com.lesson.distributed.transaction.atomikos.configure;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import java.util.Properties;

/**
 * @author zhengshijun
 * @version created on 2018/10/16.
 */
@Configuration
public class AtomikosConfigure {



    @Bean
    public AtomikosDataSourceBean masterDataSource(){
        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
        dataSourceBean.setUniqueResourceName("masterDataSource");
        dataSourceBean.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","zsj12345");
        properties.setProperty("url","jdbc:mysql://127.0.0.1:3306/test?useSSL=true");
        dataSourceBean.setXaProperties(properties);
        dataSourceBean.setMinPoolSize(5);
        dataSourceBean.setMaxPoolSize(10);
        dataSourceBean.setMaxIdleTime(60);
        return dataSourceBean;
    }

    @Bean
    public AtomikosDataSourceBean slaveDataSource(){

        AtomikosDataSourceBean dataSourceBean = new AtomikosDataSourceBean();
        dataSourceBean.setUniqueResourceName("slaveDataSource");
        dataSourceBean.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","zsj12345");
        properties.setProperty("url","jdbc:mysql://127.0.0.1:3306/weway?useSSL=true");
        dataSourceBean.setXaProperties(properties);
        dataSourceBean.setMinPoolSize(5);
        dataSourceBean.setMaxPoolSize(10);
        dataSourceBean.setMaxIdleTime(60);
        return dataSourceBean;
    }

    @Bean(initMethod = "init",destroyMethod = "close")
    public UserTransactionManager userTransactionManager(){

        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);
        return userTransactionManager;
    }
    @Bean
    public UserTransactionImp userTransactionImp(){
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        try {
            userTransactionImp.setTransactionTimeout(300);
        } catch (SystemException e) {
            e.printStackTrace();
        }
        return userTransactionImp;
    }

    @Bean
    public JtaTransactionManager jtaTransactionManager(UserTransactionManager transactionManager,UserTransactionImp userTransaction){
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setUserTransaction(userTransaction);
        jtaTransactionManager.setTransactionManager(transactionManager);
        return jtaTransactionManager;

    }
}
