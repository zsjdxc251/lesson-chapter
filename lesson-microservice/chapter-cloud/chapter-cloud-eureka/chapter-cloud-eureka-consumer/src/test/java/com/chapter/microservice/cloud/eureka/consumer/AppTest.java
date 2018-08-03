package com.chapter.microservice.cloud.eureka.consumer;

import static org.junit.Assert.assertTrue;

import com.chapter.cloud.eureka.api.model.UserInfo;
import com.chapter.microservice.cloud.eureka.consumer.service.UserInfoServiceProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootstrapConsumer.class)
public class AppTest {

    @Autowired
    private UserInfoServiceProxy userInfoServiceProxy;

    @Test
    public void test(){

        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("zsjdxc251");
        userInfo = userInfoServiceProxy.create(userInfo);

    }
}
