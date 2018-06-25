package com.chapter.distributed.dubbo.provider;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class ProviderMain {

    private static final Logger log = LoggerFactory.getLogger(ProviderMain.class);

    public static void main(String[] args){


        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext("9/provider.xml");
               // new ClassPathXmlApplicationContext("6/provider-cluster-2.xml");
        classPathXmlApplicationContext.start();

        String[] names = classPathXmlApplicationContext.getBeanDefinitionNames();
        System.out.println(StringUtils.join(names,"\n"));;

        synchronized (ProviderMain.class) {
            while (true) {
                try {
                    ProviderMain.class.wait();
                } catch (InterruptedException e) {
                    log.error("== synchronized error:", e);
                }
            }
        }
    }
}
