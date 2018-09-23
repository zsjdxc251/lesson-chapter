package com.lesson.source.pattern.adapter;

/**
 *
 *   兼容
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class AdapterSample {

    public static void main(String[] args){

        CloudService cloudService = new CloudService(CloudSDKType.AWSS);

        cloudService.uploadFile("file.zip");


    }
}
