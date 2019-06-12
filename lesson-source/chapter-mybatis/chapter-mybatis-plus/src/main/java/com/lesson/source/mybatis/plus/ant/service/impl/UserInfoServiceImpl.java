package com.lesson.source.mybatis.plus.ant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.lesson.source.mybatis.plus.ant.entity.UserInfo;
import com.lesson.source.mybatis.plus.ant.mapper.UserInfoMapper;
import com.lesson.source.mybatis.plus.ant.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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



    public void laSelect(){

        LambdaQueryWrapper<UserInfo> lambda1 = new QueryWrapper<UserInfo>().lambda();
        LambdaQueryWrapper<UserInfo> lambda2 = new LambdaQueryWrapper<>();

        LambdaQueryWrapper<UserInfo> lambda3 =  Wrappers.lambdaQuery();

        lambda1.ge(UserInfo::getAge,1);

     
        List<UserInfo> list = new LambdaQueryChainWrapper<>(baseMapper).gt(UserInfo::getPassword,"1231231").list();

    }
}
