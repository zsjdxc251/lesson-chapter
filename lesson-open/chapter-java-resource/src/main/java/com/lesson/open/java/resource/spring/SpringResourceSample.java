package com.lesson.open.java.resource.spring;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author zhengshijun
 * @version created on 2018/8/27.
 */
public class SpringResourceSample {

    private static final Logger log = LoggerFactory.getLogger(SpringResourceSample.class);

    public static void main(String[] args) throws Exception{


        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();

        String PROTOCOL_PREFIX = "cp:/";
        resourceLoader.addProtocolResolver((location, resourceLoader1) -> {

            if (location.startsWith(PROTOCOL_PREFIX)) {
                // application.properties
                String classpath = ResourceLoader.CLASSPATH_URL_PREFIX +
                        location.substring(PROTOCOL_PREFIX.length());
                // cp:/application.properties -> classpath:application.properties
                return resourceLoader.getResource(classpath);
            }
            return null;
        });

        Resource resource =
                resourceLoader.getResource("cp:/application.properties");

        InputStream inputStream = resource.getInputStream();

        String content = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));

        System.out.println(content);
        multiple();
    }


    /**
     *
     *    ClassPathResource -> 唯独获取classpath路径下面资源
     *    DefaultResourceLoader -> getResource
     *    PathMatchingResourcePatternResolver -> new DefaultResourceLoader 循环通过 -> DefaultResourceLoader#getResource
     *
     * @throws Exception
     */
    public static  void  multiple() throws Exception{

        ClassPathResource classPathResource = new ClassPathResource("application.properties");
        log.info("classPathResource:{}",classPathResource.getInputStream());
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();

        log.info("classPathResource:{}", defaultResourceLoader.getResource("classpath:application.properties").getInputStream());

        Resource[] resources = null;
        try {
            resources = new PathMatchingResourcePatternResolver()
                    .getResources(PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX+"application.properties");
        } catch (IOException e) {
            log.error(StringUtils.EMPTY,e);
        }

    }
}
