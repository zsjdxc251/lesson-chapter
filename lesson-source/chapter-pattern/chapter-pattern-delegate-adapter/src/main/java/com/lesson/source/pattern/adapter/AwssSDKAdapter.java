package com.lesson.source.pattern.adapter;

/**
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class AwssSDKAdapter implements BaseCloudSDK {

    private AwssSDK awssSDK;

    public AwssSDKAdapter(AwssSDK awssSDK) {
        this.awssSDK = awssSDK;
    }

    @Override
    public void putObject(String fileName) {
        awssSDK.putObject(fileName);
    }
}
