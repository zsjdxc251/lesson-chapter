package com.lesson;

import static org.junit.Assert.assertTrue;

import com.lesson.source.spring.type.conversion.CustomConversionService;
import org.junit.Test;
import org.springframework.core.ResolvableType;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {


        ResolvableType.forClass(CustomConversionService.class);
    }
}
