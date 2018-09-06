package com.lesson.source.spring.type.conversion.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @author zhengshijun
 * @version created on 2018/9/6.
 */
public class StringArrayToStringConverter implements Converter<String[], String> {

    @Override
    public String convert(String[] source) {
        return ObjectUtils.isEmpty(source) ? null : StringUtils.arrayToCommaDelimitedString(source);
    }
}
