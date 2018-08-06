# Spring Boot

## spring-boot-autoconfigure

  * `@ConfigurationProperties` configure `@EnableConfigurationProperties`
  * SpringBoot内置条件注解
    * @ConditionalOnBean：当SpringIoc容器内存在指定Bean的条件
    * @ConditionalOnClass：当SpringIoc容器内存在指定Class的条件
    * @ConditionalOnExpression：基于SpEL表达式作为判断条件
    * @ConditionalOnJava：基于JVM版本作为判断条件
    * @ConditionalOnJndi：在JNDI存在时查找指定的位置
    * @ConditionalOnMissingBean：当SpringIoc容器内不存在指定Bean的条件
    * @ConditionalOnMissingClass：当SpringIoc容器内不存在指定Class的条件
    * @ConditionalOnNotWebApplication：当前项目不是Web项目的条件
    * @ConditionalOnProperty：指定的属性是否有指定的值
    * @ConditionalOnResource：类路径是否有指定的值
    * @ConditionalOnSingleCandidate：当指定Bean在SpringIoc容器内只有一个，或者虽然有多个但是指定首选的Bean
    * @ConditionalOnWebApplication：当前项目是Web项目的条件
     **以上注解都是元注解@Conditional演变而来的，根据不用的条件对应创建以上的具体条件注解**