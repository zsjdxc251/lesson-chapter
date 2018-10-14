package com.chapter.common;

/**
 * @author zhengshijun
 * @version created on 2018/10/12.
 */
public class FinallyTest {


    public static void main(String[] args){

        System.out.println(getName());

    }



    public static String getName(){
        try {
            System.out.println("finally1");
            return getFullName();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally2");

        }
        return "demo2";
    }

    public static String getFullName(){
        System.out.println("finally3");
        return "demo3";
    }
}
