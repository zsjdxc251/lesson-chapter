package com.chapter.distributed.serializer;

import com.chapter.distributed.serializer.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args){

        ISerializer serializer = new JavaSerializer();

        UserInfo userInfo = new UserInfo();
        userInfo.setName("用户名");
        userInfo.setAge(18);
        userInfo.setAddress("深圳");
        userInfo.setSex("男");
        byte[] bytes = serializer.encoder(userInfo);
        log.info("序列化size:{}",bytes.length);
        userInfo = serializer.decoder(bytes,UserInfo.class);
        log.info("userInfo:{}",userInfo);

    }
}
