package com.lesson.source.spring.aop.quickstart.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author zhengshijun
 * @version created on 2018/10/26.
 */

@Component
@Aspect
public class CacheAspect {


    @Pointcut("execution(public * com.lesson.source.spring.aop.quickstart.service.*.*(..))")
    public void pointcut(){}

    @Around("pointcut()")
    public Object excute(ProceedingJoinPoint pjp){


        System.out.println();


        try {
            return pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();

        }
        return null;
    }
}
