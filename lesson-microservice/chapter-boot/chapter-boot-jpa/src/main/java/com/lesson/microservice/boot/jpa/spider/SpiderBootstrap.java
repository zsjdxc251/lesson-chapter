package com.lesson.microservice.boot.jpa.spider;

import com.google.common.io.ByteProcessor;
import com.google.common.io.ByteStreams;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengshijun
 * @version created on 2018/10/20.
 */
public class SpiderBootstrap {

    public static ThreadPoolExecutor executor =
            new ThreadPoolExecutor(1,2,60, TimeUnit.SECONDS,new LinkedBlockingDeque<>(5000));


    public static LinkedBlockingDeque<String> waitExecuteUrls = new LinkedBlockingDeque<>(5000);





    public static void main(String[] args){


        waitExecuteUrls.add("https://baike.baidu.com/item/%E6%B0%B4%E8%8F%9C%E4%B8%BD/9013698");
        String url = null;
       while (true) {

           try {
               System.out.println("take before");
               url = waitExecuteUrls.take();
               System.out.println("take after");
               if (url.equals("https://baike.baidu.com/item/%E7%99%BE%E5%BA%A6%E7%99%BE%E7%A7%91%EF%BC%9A%E6%9C%AC%E4%BA%BA%E8%AF%8D%E6%9D%A1%E7%BC%96%E8%BE%91%E6%9C%8D%E5%8A%A1/22442459?bk_fr=pcFooter")) {
                   continue;
               }
               System.out.println(url);
               executor.execute(new ProviderHandler(url));
           } catch (Exception e) {
               e.printStackTrace();
           }
       }





    }


}
