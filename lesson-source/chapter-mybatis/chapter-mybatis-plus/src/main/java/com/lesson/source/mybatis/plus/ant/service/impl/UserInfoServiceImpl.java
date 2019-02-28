package com.lesson.source.mybatis.plus.ant.service.impl;

import com.lesson.source.mybatis.plus.ant.entity.UserInfo;
import com.lesson.source.mybatis.plus.ant.mapper.UserInfoMapper;
import com.lesson.source.mybatis.plus.ant.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhengshijun
 * @since 2019-02-28
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
