package com.lesson.source.spring.type.conversion;

import com.google.common.collect.Lists;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.List;

/**
 * @author zhengshijun
 */
public class DefaultConvertSample {

    public static void main(String[] args){


        DefaultConversionService cs = new DefaultConversionService();

        List<Integer> input = Lists.newArrayList();
        input.add(1);
        Object result = cs.convert(input,TypeDescriptor.forObject(input), // List<Integer> type descriptor
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(String.class)));

        System.out.println(List.class.cast(result).get(0).getClass());

    }
}
