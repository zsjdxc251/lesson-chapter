package com.lesson.distributed.mongo.sample.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * @author zhengshijun
 * @version created on 2018/10/25.
 */
@Entity("user")
public class User {

    @Id
    private String id;
    private String name;
    private String avatar;
    private String thumbnail;
    private String currentAppOpenId;
    private String ctime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCurrentAppOpenId() {
        return currentAppOpenId;
    }

    public void setCurrentAppOpenId(String currentAppOpenId) {
        this.currentAppOpenId = currentAppOpenId;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}
