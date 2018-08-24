package com.lesson.microservice.boot.sample;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * @author zhengshijun
 */
@SpringBootApplication
public class BootstrapBootSample
{
    public static void main( String[] args ){

        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(BootstrapBootSample.class,args);

    }


}
