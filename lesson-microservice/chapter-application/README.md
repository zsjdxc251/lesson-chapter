# Spring Application
### 注解驱动

`AnnotationConfigApplicationContext`

### xml驱动

`ClassPathXmlApplicationContext`





##  spring 事件

`SimpleApplicationEventMulticaster`

```java
public void multicastEvent(final ApplicationEvent event, @Nullable ResolvableType eventType) {
    ResolvableType type = (eventType != null ? eventType : resolveDefaultEventType(event));
    for (final ApplicationListener<?> listener : getApplicationListeners(event, type)) {
        Executor executor = getTaskExecutor();
        if (executor != null) {
            executor.execute(() -> invokeListener(listener, event));
        }
        else {
            invokeListener(listener, event);
        }
    }
}
```



### SpringBoot事件

1. `org.springframework.boot.context.event.ApplicationStartingEvent`
2. `org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent`
3. `org.springframework.boot.context.event.ApplicationPreparedEvent`
4. `org.springframework.context.event.ContextRefreshedEvent`
5. `org.springframework.boot.context.event.ApplicationStartedEvent`
6. `org.springframework.boot.context.event.ApplicationReadyEvent`
7. `org.springframework.context.event.ContextClosedEvent`



### 配置文件加载

1.  `YamlPropertySourceLoader`
2.  `PropertiesPropertySourceLoader`











