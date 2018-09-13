package com.lesson.open.java.resource.protocol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * @author zhengshijun
 * @version created on 2018/8/27.
 */
public class ClasspathHandler extends URLStreamHandler {
    private static final Logger log = LoggerFactory.getLogger(ClasspathHandler.class);

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        log.info("host:{}",u.getHost());

        File file = new File("");


        Thread currentThread = Thread.currentThread();

        URL url = currentThread.getClass().getResource("/"+u.getHost());
        log.info("url:{}",url);


        return url.openConnection();
    }
}
