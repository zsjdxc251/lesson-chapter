package com.lesson.open.java.resource;

import com.google.common.collect.Streams;
import com.google.common.io.Files;
import com.google.common.primitives.Bytes;
import com.lesson.open.java.resource.protocol.ClasspathHandler;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.util.StreamUtils;


import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 *
 *          // file URLStreamHandler   = sun.net.www.protocol.file.Handler
 *         // http URLStreamHandler  =  sun.net.www.protocol.http.Handler
 *         // https URLStreamHandler  = sun.net.www.protocol.https.Handler
 *         // jar URLStreamHandler  = sun.net.www.protocol.jar.Handler
 *         // ftp URLStreamHandler  = sun.net.www.protocol.ftp.Handler
 *         // 模式 URLStreamHandler =  sun.net.www.protocol.${protocol}.Handler
 * @author zhengshijun
 * @version created on 2018/8/27.
 */
public class ResourceSample {

    public static void main(String[] args){

       try {
           URL.setURLStreamHandlerFactory(protocol -> {
               // 扩张协议
               if ("classpath".equals(protocol)){
                   return new ClasspathHandler();
               }
               return null;
           });
           URL classpath = new URL("classpath://application.properties");
           System.out.println(classpath.getPath());
           String content = StreamUtils.copyToString(classpath.openStream(), Charset.forName("UTF-8"));
           System.out.println(content);
            URL https = new URL("https://cn.bing.com");
           content = StreamUtils.copyToString(https.openStream(), Charset.forName("UTF-8"));
           System.out.println(content);

       } catch (Exception e) {
           e.printStackTrace();
       }


    }
}
