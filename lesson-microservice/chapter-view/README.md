# Spring web Mvc 视图

## Thymeleaf

* 内置变量

  `StandardExpressionObjectFactory` - > `IExpressionContext`

* 在 Spring Boot 中配置属性

  ```java
  @ConfigurationProperties(prefix = "spring.thymeleaf")
  public class ThymeleafProperties {
      ...
  	private String prefix = DEFAULT_PREFIX;
  
  	private String suffix = DEFAULT_SUFFIX;
      ...
  }
  ```

* 在 Spring 中配置

  ```xml
  <bean id="templateResolver"
         class="org.thymeleaf.templateresolver.ServletContextTemplateResolver">
    <property name="prefix" value="/WEB-INF/templates/" />
    <property name="suffix" value=".html" />
    <property name="templateMode" value="HTML5" />
  </bean>
      
  <bean id="templateEngine"
        class="org.thymeleaf.spring4.SpringTemplateEngine">
    <property name="templateResolver" ref="templateResolver" />
  </bean>
  ```

  

* 渲染逻辑

  * C   `DispatcherServlet`

  * M  `Model` 、`ModelMap` 、`ModelAndView` 、`@ModelAttribute`

* 其他视图解析器

  * JSP

    ```xml
    <bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
          <property name="viewClass" value="org.springframework.web.servlet.view.JstlView"/>
          <property name="prefix" value="/WEB-INF/jsp/"/>
          <property name="suffix" value=".jsp"/>
    </bean>
    ```

  * Freemarker
    ```xml
       <bean id="freemarkerConfig"
            class="org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer">
            <property name="freemarkerSettings" ref="freemarkerConfiguration" />
            <property name="templateLoaderPath">
                <value>/WEB-INF/templates</value>
            </property>
            <property name="freemarkerVariables">
                <map>
                    <entry key="xml_escape" value-ref="fmXmlEscape" />
                </map>
            </property>
            <property name="defaultEncoding">
                <value>utf-8</value>
            </property>
        </bean>
        <bean id="fmXmlEscape" class="freemarker.template.utility.XmlEscape" />   
    
    <bean
            class="org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver">
            <property name="viewClass"
                value="org.springframework.web.servlet.view.freemarker.FreeMarkerView" />
            <property name="contentType" value="text/html; charset=utf-8" />
            <property name="suffix" value=".ftl" />
            <property name="order" value="0" />
        </bean>
    ```

   * velocity

     ```xml
     <bean id="velocityConfigurer" class="org.springframework.web.servlet.view.velocity.VelocityConfigurer">
     
         <property name="resourceLoaderPath">
     
           <value>WEB-INF/velocity/</value>
     
         </property>
     
         </bean>
     <bean id="viewResolver" class="org.springframework.
     
               web.servlet.view.velocity.VelocityViewResolver">
     
         <property name="suffix"><value>.vm</value></property>
     
     </bean>
     ```

     



## 国际化

* 在 Spring Boot中的配置

  ```java
  @Bean
  @ConfigurationProperties(prefix = "spring.messages")
  public MessageSourceProperties messageSourceProperties() {
      return new MessageSourceProperties();
  }
  
  
  public class MessageSourceProperties {
      ...
  	private String basename = "messages";
      ...
  }
  ```



注 ：[参考文档](https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/htmlsingle/)

