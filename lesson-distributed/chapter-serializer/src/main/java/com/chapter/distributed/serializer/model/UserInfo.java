package com.chapter.distributed.serializer.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author zhengshijun
 * @version created on 2018/6/20.
 */
public class UserInfo implements Serializable{

    private static final Logger log = LoggerFactory.getLogger(UserInfo.class);

    private String name;

    private int age;

    private String address;

    // 不参与序列化
    private transient String sex;

    public UserInfo() {
    }

    public UserInfo(String name, int age, String address, String sex) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
    }

    //序列化对象
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        log.info("writeObject");
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(sex);
    }

    //反序列化
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        log.info("readObject");
        objectInputStream.defaultReadObject();
        sex=(String)objectInputStream.readObject();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
