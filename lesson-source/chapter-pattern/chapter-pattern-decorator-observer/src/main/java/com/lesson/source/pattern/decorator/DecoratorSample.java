package com.lesson.source.pattern.decorator;

import java.lang.reflect.Field;

/**
 *
 *    XXXWrapper
 *    DecoratorXXX
 *
 *    装饰器模式实际上是一种特殊的适配器模式
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class DecoratorSample {

    public static void main(String[] args){

        FileUpload fileUpload = new FileUpload();

        ZipFileUpload zipFileUpload = new ZipFileUpload(fileUpload);

        zipFileUpload.upload("xxx.zip");

    }
}
