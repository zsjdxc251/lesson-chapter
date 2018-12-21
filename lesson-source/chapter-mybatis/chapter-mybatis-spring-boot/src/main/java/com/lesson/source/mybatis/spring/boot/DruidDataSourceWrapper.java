package com.lesson.source.mybatis.spring.boot;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhengshijun
 * @version created on 2018/12/22.
 */
@ConfigurationProperties("spring.datasource.druid")
public class DruidDataSourceWrapper extends DruidDataSource implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
