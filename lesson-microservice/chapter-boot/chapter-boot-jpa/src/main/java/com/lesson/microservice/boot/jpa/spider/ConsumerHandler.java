package com.lesson.microservice.boot.jpa.spider;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2018/10/20.
 */
public class ConsumerHandler implements Runnable{

    private PageDocument pageDocument;

    public ConsumerHandler(PageDocument pageDocument) {
        this.pageDocument = pageDocument;
    }

    @Override
    public void run() {



        System.out.println(pageDocument.getContent());
        System.out.println(pageDocument.getTitle());
        List<String> urls = pageDocument.doNextUrl();
        urls.forEach(url->{
            try {


                SpiderBootstrap.waitExecuteUrls.putLast(url);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });





    }
}
