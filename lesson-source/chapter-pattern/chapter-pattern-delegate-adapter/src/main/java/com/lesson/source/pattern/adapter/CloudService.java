package com.lesson.source.pattern.adapter;

/**
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class CloudService {


    private BaseCloudSDK baseCloudSDK;

    public CloudService(CloudSDKType cloudSDKType) {
        baseCloudSDK = CloudSDKFactory.cloudSDK(cloudSDKType);
    }

    public void uploadFile(String fileName) {
        baseCloudSDK.putObject(fileName);
    }
}
