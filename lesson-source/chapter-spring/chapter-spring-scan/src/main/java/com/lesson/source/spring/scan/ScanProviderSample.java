package com.lesson.source.spring.scan;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ComponentScanBeanDefinitionParser;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.Objects;
import java.util.Set;


/**
 *  {@link ClassPathBeanDefinitionScanner}
 *
 *  {@link ComponentScanBeanDefinitionParser}
 *
 *  {@link ConfigurationClassPostProcessor#processConfigBeanDefinitions(org.springframework.beans.factory.support.BeanDefinitionRegistry)}
 */
public class ScanProviderSample {


    public static void main(String[] args) throws ClassNotFoundException {

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false); // 不使用默认的TypeFilter
        provider.addIncludeFilter(new AnnotationTypeFilter(Component.class));




        Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents("org.springframework");

        for (BeanDefinition beanDefinition: beanDefinitionSet) {

            System.out.println(beanDefinition.getClass());
            System.out.println(beanDefinition.getBeanClassName());

            ClassUtils.forName(Objects.requireNonNull(beanDefinition.getBeanClassName()),ClassUtils.getDefaultClassLoader());


        }
    }
}
