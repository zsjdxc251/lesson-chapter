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

    

## RequestResponseBodyMethodProcessor









## 请求流程

`DispatcherServlet` -> `HandlerMapping` ->  `HandlerAdapter`  -> `HandlerInterceptor` - > `HttpMessageConverter` ->`Handler` -> `View` -> `ViewRe`



