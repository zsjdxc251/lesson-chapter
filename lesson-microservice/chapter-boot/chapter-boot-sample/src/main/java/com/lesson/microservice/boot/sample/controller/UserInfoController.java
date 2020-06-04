package com.lesson.microservice.boot.sample.controller;

import com.alibaba.fastjson.JSON;
import com.lesson.microservice.boot.sample.configure.CustomProperties;
import com.lesson.microservice.boot.sample.entity.UserInfo;
import com.lesson.microservice.boot.sample.repository.UserInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhengshijun
 * @version created on 2018/8/24.
 */
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private CustomProperties customProperties;

    /**
     * {@link org.hibernate.validator.constraints.Range}
     *
     * {@link Valid}
     * @param userInfo
     * @return
     */
    @GetMapping("/getName")
    public ResponseEntity<String> getName(@Validated UserInfo userInfo){
        //userInfoRepository.find();
        CustomProperties customProperties = new CustomProperties();
        BeanUtils.copyProperties(this.customProperties,customProperties);
        return ResponseEntity.ok(JSON.toJSONString(customProperties));
    }
}
