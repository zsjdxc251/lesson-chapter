package com.lesson.source.pattern.adapter;

/**
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class AliyunSDK {

    public void setBucket(int index){
        System.out.println("使用阿里云SDK 设置 bucket:"+index);

    }

    public void updateFile(String fileName) {
        System.out.println("使用阿里云SDK 上传文件："+fileName);
    }
}
