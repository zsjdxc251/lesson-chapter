package com.lesson.distributed.redis.sample;

import static org.junit.Assert.*;

public class ZedisTest {


    public static void main(String[] args){
        Zedis customJedis = new Zedis("192.168.1.20", 6380, "dmzdwzidhkakkjnmkvxiyzcsbyrspbadSmznltzvmkou2sjzhzjtxyoBydvkpbgbegxif7zxkcehacqnkazk]uqdzxmxdniwkocyhylnqxwnyqgwwxdewydpwkrfw");

        System.out.println(customJedis.isConnected());

        System.out.println(customJedis.set("caicaicai:test", "china"));
        System.out.println("get:"+customJedis.get("caicaicai:test"));
        customJedis.mdel("caicaicai:test");
        System.out.println("get:"+customJedis.get("caicaicai:test"));
        customJedis.close();

    }

}