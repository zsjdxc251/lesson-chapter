package com.lesson.distributed.redis.sample.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

/**
 * @author zhengshijun
 * @version created on 2018/9/28.
 */
public class BloomSample {

    public static void main(String[] args){
        BloomFilter<String> filter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()),10000000,0.01);


        filter.put("222");


        System.out.println(filter.mightContain("222"));




    }
}
