package com.lesson.source.pattern.command;

/**
 * @author zhengshijun
 * @version created on 2018/10/1.
 */
public class Document {

    public void init(){

        System.out.println("文档初始化");

    }

    public void open(){

        System.out.println("文档打开");
    }

    public void create(){
        System.out.println("文档创建");

    }

    public void close(){
        System.out.println("文档关闭");
    }

    public void copy(){

        System.out.println("文档拷贝");
    }
}
