package com.chapter.distributed.zookeeper.rpc;

/**
 * @author zhengshijun
 * @version created on 2018/9/11.
 */
public interface IRegisterCenter {
    /**
     * 注册地址
     *
     * @param serviceName
     * @param serviceAddress
     */
    void register(String serviceName, String serviceAddress);
}
