package com.lesson.open.java.beans.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * @author zhengshijun
 * @version created on 2018/10/27.
 */
public class MyDateFormatter implements Formatter<Date> {

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(Date object, Locale locale) {
        return null;
    }
}
