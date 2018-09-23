package com.lesson.source.pattern.adapter;

/**
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class AliyunSDKAdapter implements BaseCloudSDK {

    private AliyunSDK aliyunSDK;

    public AliyunSDKAdapter(AliyunSDK aliyunSDK) {
        this.aliyunSDK = aliyunSDK;
    }

    @Override
    public void putObject(String fileName) {
        aliyunSDK.setBucket(1);
        aliyunSDK.updateFile(fileName);

    }
}
