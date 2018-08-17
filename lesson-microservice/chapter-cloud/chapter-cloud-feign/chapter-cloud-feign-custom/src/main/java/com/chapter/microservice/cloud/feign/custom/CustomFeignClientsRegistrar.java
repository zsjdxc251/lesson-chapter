package com.chapter.microservice.cloud.feign.custom;

import com.chapter.microservice.cloud.feign.custom.annotation.CustomEnableFeignClients;
import com.chapter.microservice.cloud.feign.custom.annotation.CustomFeignClient;
import org.hamcrest.Factory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.stream.Stream;

public class CustomFeignClientsRegistrar implements ImportBeanDefinitionRegistrar , BeanFactoryAware {


    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata,
                                        BeanDefinitionRegistry registry) {



        ClassLoader classLoader = metadata.getClass().getClassLoader();



        Map<String, Object> attributes =
                metadata.getAnnotationAttributes(CustomEnableFeignClients.class.getName());



        Object clientsObject = attributes.get("clients");
        Class<?>[] classes = (Class<?>[])clientsObject;

        Stream.of(classes).filter(Class::isInterface)
                .filter(client->client.getAnnotation(CustomFeignClient.class) != null)
                .forEach(client->{

            CustomFeignClient customFeignClient =
                    client.getAnnotation(CustomFeignClient.class);

            String serviceName = customFeignClient.name();


            Object proxy = Proxy.newProxyInstance(classLoader,new Class[]{client},new RequestMappingMethodInvocationHandler(serviceName,beanFactory));

            String beanName = serviceName.concat(".").concat(client.getSimpleName());
            if (registry instanceof SingletonBeanRegistry){
                SingletonBeanRegistry.class.cast(registry).registerSingleton(beanName,proxy);
            } else {

                BeanDefinitionBuilder beanDefinitionBuilder =
                        BeanDefinitionBuilder.genericBeanDefinition(RegistrarCustomFeignClient.class);
                beanDefinitionBuilder.addConstructorArgValue(proxy);
                beanDefinitionBuilder.addConstructorArgValue(client);
                BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
                registry.registerBeanDefinition(beanName,beanDefinition);


            }
        });



    }



    private static class RegistrarCustomFeignClient implements FactoryBean<Object> {

        public final Object proxy;

        private final Class<?> feignClient;

        public RegistrarCustomFeignClient(Object proxy, Class<?> feignClient) {
            this.proxy = proxy;
            this.feignClient = feignClient;
        }

        @Override
        public Object getObject() throws Exception {
            return proxy;
        }

        @Override
        public Class<?> getObjectType() {
            return feignClient;
        }
    }
}
