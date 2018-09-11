package com.chapter.distributed.zookeeper.rpc;

import org.junit.Test;

import static org.junit.Assert.*;

public class ZookeeperRegisterCenterTest {

    @Test
    public void register() throws Exception {


        IRegisterCenter registerCenter = new ZookeeperRegisterCenter();

        registerCenter.register("com.chapter.distributed.zookeeper.rpc.IRegisterCenter","localhost:12121");



    }
}