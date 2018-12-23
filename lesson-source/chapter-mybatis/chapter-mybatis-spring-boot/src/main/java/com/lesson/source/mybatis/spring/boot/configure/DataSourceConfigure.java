package com.lesson.source.mybatis.spring.boot.configure;

import com.alibaba.druid.pool.DruidDataSource;
import com.lesson.source.mybatis.spring.boot.DruidDataSourceWrapper;
import com.lesson.source.mybatis.spring.boot.properties.DataSourceProperties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ResolvableType;

/**
 *
 *  {@link ApplicationContextInitializer}
 *  {@link BeanDefinitionRegistryPostProcessor}
 *  {@link Lifecycle}
 *  {@link ResolvableType}
 *  {@link ConfigurationProperties}
 *  {@link org.springframework.boot.autoconfigure.condition.ConditionalOnProperty}
 * @author zhengshijun
 * @version created on 2018/12/21.
 */
//@Configuration
@EnableConfigurationProperties({DataSourceProperties.class})
public class DataSourceConfigure implements BeanFactoryAware, InitializingBean {

    private DefaultListableBeanFactory beanFactory;

    @Autowired
    private DataSourceProperties dataSourceProperties;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory)beanFactory;

    }


    @Override
    public void afterPropertiesSet() throws Exception {


        BeanDefinitionBuilder beanDefinitionBuilder =
                BeanDefinitionBuilder.genericBeanDefinition(DruidDataSourceWrapper.class);
        beanDefinitionBuilder.setScope(ConfigurableBeanFactory.SCOPE_SINGLETON);
        beanDefinitionBuilder.setInitMethodName("init");
        beanDefinitionBuilder.setDestroyMethodName("close");

        dataSourceProperties.forEach((key,value)->{

            beanDefinitionBuilder.addPropertyValue(String.valueOf(key),value);



        });

        beanFactory.registerBeanDefinition("bean11111",beanDefinitionBuilder.getRawBeanDefinition());

        DruidDataSource druidDataSource = (DruidDataSource) beanFactory.getBean("bean11111");


    }
}
