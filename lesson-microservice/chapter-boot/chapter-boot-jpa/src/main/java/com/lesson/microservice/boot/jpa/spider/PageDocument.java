package com.lesson.microservice.boot.jpa.spider;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2018/10/20.
 */
public class PageDocument {

    private Document document;

    private String url;


    public PageDocument(String url,String body) {
        this.url = url;
        document = Jsoup.parse(body);
    }

    public String getTitle(){
        return document.select(".lemmaWgt-lemmaTitle-title").select("h1").text();
    }

    public String getContent(){

        return document.select(".lemma-summary").text();
    }

    public String url(){

        return url;
    }


    public List<String> doNextUrl(){


        Elements elements = document.select("a[href^=/item/]");


        List<String> doNextUrls = Lists.newArrayList();
        String nextUrl = null;

        String domain = StringUtils.substringBeforeLast(url,"/item/");

        for(Element element : elements) {
            nextUrl =  element.attr("href");
            doNextUrls.add(domain+nextUrl);
        }
        return doNextUrls;
    }


}
