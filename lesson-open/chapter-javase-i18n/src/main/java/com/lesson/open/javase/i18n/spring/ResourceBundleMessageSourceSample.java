package com.lesson.open.javase.i18n.spring;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * @author zhengshijun
 * @version created on 2019/6/5.
 */
public class ResourceBundleMessageSourceSample {

	public static void main(String[] args) {


		String baseName = "locale.messages";
		// ResourceBundle + MessageFormat => MessageSource
		// ResourceBundleMessageSource 不能重载
		// ReloadableResourceBundleMessageSource
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename(baseName);
		messageSource.setDefaultEncoding(StandardCharsets.UTF_8.displayName());
		System.out.println(messageSource
				.getMessage("address", new Object[]{"南山"}, Locale.getDefault()));




	}
}
