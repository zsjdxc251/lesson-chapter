package com.lesson.source.pattern.adapter;

/**
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public enum CloudSDKType{
    ALIYUN(new AliyunSDKAdapter(new AliyunSDK())),AWSS(new AwssSDKAdapter(new AwssSDK()));

    private BaseCloudSDK baseCloudSDK;

    CloudSDKType(BaseCloudSDK baseCloudSDK) {
        this.baseCloudSDK = baseCloudSDK;
    }

    public BaseCloudSDK getCloudSdk(){
        return baseCloudSDK;
    }
}
