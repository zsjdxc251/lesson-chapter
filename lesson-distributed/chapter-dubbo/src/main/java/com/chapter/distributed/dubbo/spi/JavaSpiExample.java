package com.chapter.distributed.dubbo.spi;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author zhengshijun
 * @version created on 2018/6/21.
 */
public class JavaSpiExample {


    public static void main(String[] args){
        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);

        Iterator<Driver> searchs = serviceLoader.iterator();
        Driver driver;
        if (searchs.hasNext()) {
            driver = searchs.next();
            System.out.println(driver.getClass());
        }

    }
}
