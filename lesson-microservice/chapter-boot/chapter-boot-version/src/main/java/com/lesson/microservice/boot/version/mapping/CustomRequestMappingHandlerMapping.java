package com.lesson.microservice.boot.version.mapping;


import com.lesson.microservice.boot.version.annotation.ApiVersion;
import com.lesson.microservice.boot.version.condition.ApiVersionCondition;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author zhengshijun
 */
public class CustomRequestMappingHandlerMapping extends RequestMappingHandlerMapping {


    /**
     *   ①
     * @param handlerType
     * @return
     */
    @Override
    protected RequestCondition<ApiVersionCondition> getCustomTypeCondition(Class<?> handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createCondition(apiVersion);
    }

    /**
     *  ②
     * @param method
     * @return
     */
    @Override
    protected RequestCondition<ApiVersionCondition> getCustomMethodCondition(Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createCondition(apiVersion);
    }


    /**
     *  ③
     * @param apiVersion
     * @return
     */
    private RequestCondition<ApiVersionCondition> createCondition(ApiVersion apiVersion) {
        return apiVersion == null ? null : new ApiVersionCondition(apiVersion.value());
    }


}
