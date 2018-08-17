package com.chapter.common.tools;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhengshijun
 * @version created on 2018/7/21.
 */
public final class StringTools {


    /**
     * 首字母转为小写
     * ASCII 码中 大写与小写相差32
     * @param arg
     * @return
     */
    public static String initialToLowerCase(String arg){
        char[] args = arg.toCharArray();
        args[0]+=32;
        return String.valueOf(args);
    }

    /**
     * 是否全英文字母
     *
     * @param arg
     * @return
     */
    public static boolean isLetters(String arg){
        return arg.matches("[a-z,A-Z]+");
    }

    /**
     * 替换连续斜杆
     *
     * @param arg
     * @return
     */
    public static String replaceSeries(String arg){
        return arg.replaceAll("/+","/");
    }


    public static void main(String[] args){

        System.out.println(replaceSeries("https://blog.csdn.net/edison_03//article/details/75009574"));

        System.out.println(isLetters("ab+"));



    }
}
