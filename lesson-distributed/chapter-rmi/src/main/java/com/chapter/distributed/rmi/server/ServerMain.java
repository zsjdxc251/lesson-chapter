package com.chapter.distributed.rmi.server;

import com.chapter.distributed.rmi.server.impl.UserInfoService;
import com.chapter.distributed.rmi.service.IUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class ServerMain {

    private static final Logger log = LoggerFactory.getLogger(ServerMain.class);

    public static void main(String[] args){

       try {

          // LocateRegistry.createRegistry(8080);



           IUserInfoService userInfoService = new UserInfoService();

           Naming.rebind("rmi://127.0.0.1:8080/Hello",userInfoService); //注册中心 key - value
       } catch (Exception e) {
           log.error(StringUtils.EMPTY,e);
       }

    }
}
