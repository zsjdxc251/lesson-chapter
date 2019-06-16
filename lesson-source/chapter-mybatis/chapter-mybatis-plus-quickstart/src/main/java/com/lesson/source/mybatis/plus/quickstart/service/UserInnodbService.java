package com.lesson.source.mybatis.plus.quickstart.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lesson.source.mybatis.plus.quickstart.entity.UserInnodb;
import com.lesson.source.mybatis.plus.quickstart.mapper.UserInnodbMapper;
import org.springframework.stereotype.Service;

/**
 * @author zhengshijun
 * @version created on 2019/6/16.
 */
@Service
public class UserInnodbService extends ServiceImpl<UserInnodbMapper, UserInnodb> {
}
