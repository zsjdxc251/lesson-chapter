package com.lesson.source.pattern.adapter;

/**
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class CloudSDKFactory {

    public static BaseCloudSDK cloudSDK(CloudSDKType cloudSDKType) {
        return cloudSDKType.getCloudSdk();
    }
}
