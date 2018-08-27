package com.lesson.open.java.resource;

import java.io.File;
import java.net.URI;
import java.net.URL;

/**
 * @author zhengshijun
 */
public class FileResource {


    public static void main(String[] args) throws Exception{
        File file = new File("src/");
        URL url = file.toURI().toURL();
        System.out.println(url);



//
//        System.getProperties().stringPropertyNames().forEach(key->{
//            System.out.println(key+"="+System.getProperty(key));
//        });
//        System.out.println(file.getAbsolutePath());





    }
}
