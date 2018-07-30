package com.chapter.distributed.zookeeper;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author zhengshijun
 * @version created on 2018/7/27.
 */
public class ConnectionSample {

    private static final Logger log = LoggerFactory.getLogger(ConnectionSample.class);
    public static void main(String[] args){

        String url = "";
        try {
            ZooKeeper zooKeeper = new ZooKeeper(url,4000,null);




        } catch (IOException e) {
            log.error(StringUtils.EMPTY,e);
        }

    }
}
