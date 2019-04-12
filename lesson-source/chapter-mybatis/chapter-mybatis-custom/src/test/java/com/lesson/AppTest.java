package com.lesson;

import static org.junit.Assert.assertTrue;

import com.lesson.source.mybatis.custom.UserService;
import com.lesson.source.mybatis.custom.utils.NamingUtils;
import org.junit.Test;

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
        assertTrue( true );
    }


    @Test
    public void test1(){


        System.out.println(NamingUtils.humpToUnderline("userName"));

        System.out.println(NamingUtils.underlineToHump("user_name"));

    }

    @Test
    public void test2(){

        Class<?> clazz = UserService.class;
        try {
            System.out.println(clazz.getMethod("insert",Object.class).isBridge());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
