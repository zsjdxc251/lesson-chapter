package com.chapter.distributed.rmi.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public interface IUserInfoService extends Remote {

    String getName(String lastName)throws RemoteException;
}
