package com.chapter.distributed.activemq.spring;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.jms.annotation.JmsListener;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * @author zhengshijun
 * @version created on 2018/6/12.
 */
public class InitialBean implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {

        Class<?> targetClass = AopUtils.getTargetClass(bean);
        System.out.println(targetClass.getName());
        Map<Method, Set<JmsListener>> annotatedMethods = MethodIntrospector.selectMethods(targetClass,
                new MethodIntrospector.MetadataLookup<Set<JmsListener>>() {
                    @Override
                    public Set<JmsListener> inspect(Method method) {

                        Set<JmsListener> listenerMethods = AnnotatedElementUtils.getMergedRepeatableAnnotations(
                                method, JmsListener.class);

                        if (!listenerMethods.isEmpty()){
                            System.out.println("lin");

                        }

                        return (!listenerMethods.isEmpty() ? listenerMethods : null);
                    }
                });

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        return bean;
    }
}
