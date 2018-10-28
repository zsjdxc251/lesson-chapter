package com.lesson.open.java.beans.formatter;

import org.springframework.format.number.CurrencyStyleFormatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

/**
 * @author zhengshijun
 * @version created on 2018/10/27.
 */
public class FormatterSample {


    public static void main(String[] args) throws Exception{

        CurrencyStyleFormatter currencyStyleFormatter = new CurrencyStyleFormatter();

        currencyStyleFormatter.setFractionDigits(2);
        currencyStyleFormatter.setRoundingMode(RoundingMode.CEILING);

        System.out.println(currencyStyleFormatter.parse("$123.123", Locale.US));



        System.out.println(currencyStyleFormatter.print(new BigDecimal("123.12"),Locale.US));

    }
}
