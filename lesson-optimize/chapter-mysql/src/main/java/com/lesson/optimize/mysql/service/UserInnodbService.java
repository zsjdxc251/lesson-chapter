package com.lesson.optimize.mysql.service;

import com.lesson.optimize.mysql.entity.UserInnodb;
import com.lesson.optimize.mysql.repository.UserInnodbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

/**
 * @author zhengshijun
 * @version created on 2019/6/16.
 */
@Service
public class UserInnodbService {


    @Autowired
    private UserInnodbRepository repository;


    @Transactional(rollbackFor = Exception.class,propagation = REQUIRES_NEW)
    public Long save(UserInnodb userInnodb){
        userInnodb = repository.save(userInnodb);

        return userInnodb.getId();
    }
}
