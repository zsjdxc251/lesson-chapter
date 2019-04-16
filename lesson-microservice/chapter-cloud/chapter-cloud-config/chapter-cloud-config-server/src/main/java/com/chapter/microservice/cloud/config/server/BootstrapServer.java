package com.chapter.microservice.cloud.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentController;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 *
 *
 *   {@link EnvironmentController}
 * @author zhengshijun
 * @version created on 2018/12/22.
 *
 *
 */
@SpringBootApplication
@EnableConfigServer
@EnableScheduling
@EnableDiscoveryClient
public class BootstrapServer
{
    public static void main( String[] args ){

        SpringApplication.run(BootstrapServer.class,args);
    }


    /**
     * 自定义数据源 config server
     * @return
     */
    @Bean
    public EnvironmentRepository environmentRepository(){

        return (String application, String profile, String label)->{

            Environment environment = new Environment("custom-environment", profile);
            List<PropertySource> propertySources = environment.getPropertySources();


            Map<String, Object> source = new HashMap<>();
            source.put("userName","custom-userName");
            PropertySource propertySource = new PropertySource("custom-map",source);

            propertySources.add(propertySource);
            return environment;
        };
    }

}
