package com.lesson.microservice.boot.sample.configure;

import com.lesson.microservice.boot.sample.entity.UserInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @author zhengshijun
 * @version created on 2018/8/24.
 */
@Configuration
@ConfigurationProperties(prefix = "custom.property")
public class CustomProperties {

    private List<UserInfo> userInfoList;

    private Map<String,UserInfo> userInfoMap;

    private List<String> ids;

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }

    public Map<String, UserInfo> getUserInfoMap() {
        return userInfoMap;
    }

    public void setUserInfoMap(Map<String, UserInfo> userInfoMap) {
        this.userInfoMap = userInfoMap;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
