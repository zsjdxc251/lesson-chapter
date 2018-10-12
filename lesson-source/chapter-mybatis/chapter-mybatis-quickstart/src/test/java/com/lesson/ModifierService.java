package com.lesson;

/**
 * @author zhengshijun
 * @version created on 2018/10/12.
 */
public interface ModifierService {

    default void demo(){
        System.out.println("demo");
    }
}
