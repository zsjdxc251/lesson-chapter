package com.chapter.microservice.cloud.feign.client;


import com.chapter.microservice.cloud.feign.client.impl.UserInfoService;
import com.chapter.microservice.cloud.zookeeper.api.model.UserInfo;
import com.chapter.microservice.cloud.zookeeper.api.service.IUserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Method;

public class ApplicationTest {


    @Test
    public void test1(){


        Method method = ReflectionUtils.findMethod(UserInfoService.class,"customCreate", UserInfo.class);

        ParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();


        String[] args = new LocalVariableTableParameterNameDiscoverer().getParameterNames(method);
        System.out.println(StringUtils.join(discoverer.getParameterNames(method),"\n"));

        System.out.println(StringUtils.join(args,"\n"));
    }


}