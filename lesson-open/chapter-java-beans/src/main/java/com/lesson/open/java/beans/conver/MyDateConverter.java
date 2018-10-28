package com.lesson.open.java.beans.conver;

import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * @author zhengshijun
 * @version created on 2018/10/27.
 */
public class MyDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        return null;
    }
}
