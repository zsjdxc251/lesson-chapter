package com.lesson.distributed.transaction.atomikos.mapper.slave;

import com.lesson.distributed.transaction.atomikos.entity.UserInfo;

/**
 * @author zhengshijun
 * @version created on 2018/10/16.
 */
public interface UserInfoMapper {

    UserInfo selectOne();

    int insert(UserInfo userInfo);
}
