package com.lesson.open.javase.i18n;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ServiceLoader;
import java.util.spi.ResourceBundleControlProvider;

/**
 * @author zhengshijun
 * @version created on 2019/6/5.
 */
public class ResourceBundleSample {

	public static void main(String[] args) throws IOException {

		String name = "locale.messages";


		ResourceBundle resourceBundle = ResourceBundle.getBundle(name);



		String str = resourceBundle.getString("password");
		System.out.println(str);





		// META-INF/services/java.util.spi.ResourceBundleControlProvider


//		ClassLoader cl = ClassLoader.getSystemClassLoader();
//		ClassLoader prev = null;
//		while (cl != null) {
//			prev = cl;
//			cl = cl.getParent();
//
//		}
//
//		String path = "META-INF/services/java.util.spi.ResourceBundleControlProvider";
//		Enumeration<URL> urls  =  prev.getResources(path);
//
//		while (urls.hasMoreElements()) {
//
//			System.out.println(urls.nextElement().getPath());
//		}
//
//		ServiceLoader<ResourceBundleControlProvider> serviceLoaders
//				= ServiceLoader.load(ResourceBundleControlProvider.class);
//
//		for (ResourceBundleControlProvider provider : serviceLoaders) {
//
//			System.out.println(provider);
//		}

	}
}
