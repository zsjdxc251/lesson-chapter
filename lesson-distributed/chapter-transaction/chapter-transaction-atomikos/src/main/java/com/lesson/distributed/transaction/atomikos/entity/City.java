package com.lesson.distributed.transaction.atomikos.entity;

/**
 * @author zhengshijun
 * @version created on 2018/10/16.
 */
public class City {

    private String name;

    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
