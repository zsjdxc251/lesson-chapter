package com.chapter.distributed.zookeeper.rpc;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhengshijun
 * @version created on 2018/9/11.
 */
public class ZookeeperRegisterCenter implements IRegisterCenter {

    private static final Logger log = LoggerFactory.getLogger(ZookeeperRegisterCenter.class);

    @Override
    public void register(String serviceName, String serviceAddress) {

        CuratorFramework curatorFramework = ZookeeperFactory.createCuratorFramework();

        String servicePath = "/"+serviceName;
        try {
            Stat stat = curatorFramework.checkExists().forPath(servicePath);
            if (stat == null){
                curatorFramework.create().forPath(servicePath);
            }
            String registerPath = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(servicePath+"/"+serviceAddress);
            log.info("registerPath:{}",registerPath);
        } catch (Exception e) {
            log.error(StringUtils.EMPTY,e);
        }

    }
}
