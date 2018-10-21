package com.lesson.microservice.boot.jpa.spider;

import org.jsoup.Jsoup;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author zhengshijun
 * @version created on 2018/10/20.
 */
public class ProviderHandler implements Runnable{

    private String url;

    public ProviderHandler(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        try {
            String doUrl = url;

            URL url = new URL(doUrl);


            URLConnection urlConnection = url.openConnection();
            urlConnection.setConnectTimeout(3000);
            urlConnection.setReadTimeout(3000);


            InputStream inputStream = urlConnection.getInputStream();


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;

            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            String body = new String(outputStream.toByteArray());
            outputStream.close();
            inputStream.close();
            SpiderBootstrap.executor.execute(new ConsumerHandler(new PageDocument(doUrl,body)));
        } catch (IOException e) {
            System.out.println(url);
            e.printStackTrace();
        }
    }
}
