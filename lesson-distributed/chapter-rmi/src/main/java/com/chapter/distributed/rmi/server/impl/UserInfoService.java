package com.chapter.distributed.rmi.server.impl;

import com.chapter.distributed.rmi.service.IUserInfoService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class UserInfoService extends UnicastRemoteObject implements IUserInfoService {

    public UserInfoService() throws RemoteException {
        super();
    }

    @Override
    public String getName(String lastName) throws RemoteException{
        return "I am " +lastName;
    }
}
