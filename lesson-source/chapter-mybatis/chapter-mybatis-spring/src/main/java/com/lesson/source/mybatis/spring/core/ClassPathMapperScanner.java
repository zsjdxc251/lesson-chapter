package com.lesson.source.mybatis.spring.core;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.asm.ClassReader;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;
import org.springframework.util.ClassUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author zhengshijun
 * @version created on 2019/4/23.
 */
@Slf4j
public class ClassPathMapperScanner {


	private PathMatchingResourcePatternResolver resourcePatternResolver;

	private ResourceLoader resourceLoader;


	public Stream<Class<?>> doScan(String... basePackages) {

		Set<Class<?>> mapperClass = Sets.newHashSet();

		Stream.of(basePackages).forEach(basePackage -> {

			String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
					ClassUtils.convertClassNameToResourcePath(new StandardEnvironment().resolveRequiredPlaceholders(basePackage)) + '/' + "**/*.class";

			try {
				Resource[] resources = getResourcePatternResolver().getResources(packageSearchPath);

				Stream.of(resources).forEach(resource -> {
					ClassReader classReader;
					InputStream is = null;
					try {
						is = new BufferedInputStream(resource.getInputStream());
						classReader = new ClassReader(is);
						AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor(getResourceLoader().getClassLoader());
						classReader.accept(visitor, ClassReader.SKIP_DEBUG);

						Class<?> resolvedClass = ClassUtils.forName(visitor.getClassName(), ClassUtils.getDefaultClassLoader());
						if (resolvedClass.isInterface()) {
							mapperClass.add(resolvedClass);
						}
					} catch (IOException | ClassNotFoundException e) {
						log.error(StringUtils.EMPTY, e);
					} finally {
						try {
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				});
			} catch (IOException e) {
				log.error(StringUtils.EMPTY, e);
			}

		});
		return mapperClass.stream();
	}

	private ResourcePatternResolver getResourcePatternResolver() {

		if (resourcePatternResolver == null) {
			resourcePatternResolver = new PathMatchingResourcePatternResolver();
		}
		return resourcePatternResolver;
	}

	private ResourceLoader getResourceLoader() {

		if (resourceLoader == null) {
			resourceLoader = new DefaultResourceLoader();
		}
		return resourceLoader;
	}
}
