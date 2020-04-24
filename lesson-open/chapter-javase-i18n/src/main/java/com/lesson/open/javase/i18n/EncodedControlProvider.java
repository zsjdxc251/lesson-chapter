package com.lesson.open.javase.i18n;

import java.util.ResourceBundle;
import java.util.spi.ResourceBundleControlProvider;

/**
 * @author zhengshijun
 * @version created on 2019/6/5.
 */
public class EncodedControlProvider implements ResourceBundleControlProvider  {
	@Override
	public ResourceBundle.Control getControl(String baseName) {

		System.out.println(baseName);
		return new EncodedControl();
	}
}
