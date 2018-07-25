# Spring REST

## HttpMessageConverter

* 自定义`HttpMessageConverter` 

  * 在 WebMvcConfig 中添加

    ```java
    @Configuration
    public class CustomWebMvcConfigurer implements WebMvcConfigurer {
     
        @Override
        public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
     
            converters.add(new CustomHttpMessageConverter());
            log.info("configureMessageConverters converters size:{}",converters.size());
    
        }
     
        @Override
        public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
            log.info("extendMessageConverters converters size:{}",converters.size());
        }
    }
    ```

  * @RequestMappng 中的 consumes 对应 请求头 “Content-Type”

  * @RequestMappng 中的 produces   对应 请求头 “Accept”

  * `RequestResponseBodyMethodProcessor` 处理MessageConverter核心处理类

    ```java
    public class RequestResponseBodyMethodProcessor extends AbstractMessageConverterMethodProcessor {
        protected <T> void writeWithMessageConverters(@Nullable T value, MethodParameter returnType,
    			ServletServerHttpRequest inputMessage, ServletServerHttpResponse outputMessage)
    			throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
    
    }
    ```

    

    








## 请求流程

`DispatcherServlet` -> `HandlerMapping` ->  `HandlerAdapter`  -> `HandlerInterceptor` - > `HttpMessageConverter` ->`Handler` -> `View` -> `ViewRe`

```java
public class DispatcherServlet extends FrameworkServlet {
    protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws       Exception {
       ...
    }
}
```





## 请求头信息

*  `MultiValueMap`  可支持一个key 值有多个 Value ,对应着HttpHeader 

  ```java
  public class HttpHeaders implements MultiValueMap<String, String>, Serializable {
      ...
  }
  ```



* `PATCH` Method servlet Api 没有对其做处理 spring MVC做了特殊处理

  ```java
  public abstract class FrameworkServlet extends HttpServletBean implements ApplicationContextAware {
      ...
      @Override
  	protected void service(HttpServletRequest request, HttpServletResponse response)
  			throws ServletException, IOException {
  
  		HttpMethod httpMethod = HttpMethod.resolve(request.getMethod());
  		if (httpMethod == HttpMethod.PATCH || httpMethod == null) {
  			processRequest(request, response);
  		}
  		else {
  			super.service(request, response);
  		}
  	}
      ...
  }
  ```

  

## @AliasFor

* [文档](https://github.com/spring-projects/spring-framework/wiki/Spring-Annotation-Programming-Model#attribute-aliases-and-overrides%EF%BC%89)

* 该注解用于派生性注解属性值，可以使用该注解在注解类中给其他值给指定值添加别名

  ```java
  public @interface ContextConfiguration {
  
      @AliasFor("locations")
      String[] value() default {};
  
      @AliasFor("value")
      String[] locations() default {};
  }
  ```
  上面意思是 给 value 设置值同理给 locations 设置值

* 可以应用在其他派生类中属性

  ```java
  @Controller
  @RequestMapping
  public @interface CustomAnnotation {
  
  
      @AliasFor(annotation = RequestMapping.class,attribute = "value")
      String[] paths();
  
  
      @AliasFor(annotation = Controller.class,attribute = "value")
      String beanName() default "";
  }
  ```






