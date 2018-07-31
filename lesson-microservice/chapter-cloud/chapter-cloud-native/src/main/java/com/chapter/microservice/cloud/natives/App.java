package com.chapter.microservice.cloud.natives;

import com.google.common.collect.Maps;
import javafx.beans.property.MapProperty;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.bootstrap.BootstrapConfiguration;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class App 
{
    public static void main( String[] args ) {


        ConfigurableApplicationContext configurableApplicationContext = new GenericApplicationContext();
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.setId("custom-application-context");
        applicationContext.refresh();

        ConfigurableApplicationContext context = new SpringApplicationBuilder(App.class).parent(applicationContext).run(args);

        context.getParent();

    }




    @Configuration
    public static class CustomPropertySourceLocator implements PropertySourceLocator {
        @Override
        public PropertySource<?> locate(Environment environment) {
            Map<String,Object> map = Maps.newHashMap();
            map.put("server.port","7050");
            return new MapPropertySource("customPropertySource",map);
        }
    }
}
