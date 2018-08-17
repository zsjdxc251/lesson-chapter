package com.chapter.microservice.cloud.feign.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.IntFunction;
import java.util.stream.Stream;

public class RequestMappingMethodInvocationHandler implements InvocationHandler {
    
    private static final Logger log = LoggerFactory.getLogger(RequestMappingMethodInvocationHandler.class);


    private final String serviceName;

    private final BeanFactory beanFactory;

    public RequestMappingMethodInvocationHandler(String serviceName, BeanFactory beanFactory) {
        this.serviceName = serviceName;
        this.beanFactory = beanFactory;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {



        RequestMapping requestMapping =
                requestMapping = AnnotatedElementUtils.findMergedAnnotation(method,RequestMapping.class);

       // GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);



        RequestMethod requestMethod = requestMapping.method()[0];
        Parameter[] parameters = method.getParameters();


        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append("http://").append(serviceName).append(requestMapping.value()[0]);

        String[] parentNames =
                Stream.of(parameters).map(Parameter::getName).
                        toArray(value ->  new String[value]);
        if (requestMethod == RequestMethod.GET){
            urlBuilder.append("?");
            for (int i=0;i<parentNames.length;i++){
                Object value = args[i];
                urlBuilder.append("&").append(parentNames[i]).append("=")
                        .append(String.valueOf(value));
            }

        }

        Class<?> returnType = method.getReturnType();



        Object result = null;
        try {
            result = execute(urlBuilder.toString(),requestMethod,returnType);
        } catch (URISyntaxException e) {

        }
        return result;
    }


    private <T> T execute(String url ,RequestMethod requestMethod ,Class<T> returnType) throws URISyntaxException {
        log.info("url:{}",url);
        RestTemplate restTemplate = (RestTemplate)beanFactory.getBean("loadBalancedRestTemplate");

        HttpMethod httpMethod = HttpMethod.resolve(requestMethod.name());
        RequestEntity<T> requestEntity = new RequestEntity<>(httpMethod,new URI(url));
        ResponseEntity<T> result = restTemplate.exchange(requestEntity, returnType);
        return result.getBody();
    }
 }
