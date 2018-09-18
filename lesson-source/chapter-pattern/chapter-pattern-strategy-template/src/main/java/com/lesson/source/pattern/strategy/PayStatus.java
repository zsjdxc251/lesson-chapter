package com.lesson.source.pattern.strategy;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhengshijun
 * @version created on 2018/9/18.
 */
@Setter
@Getter
@ToString
public class PayStatus {

    private int code;

    private String des;

    private double amount;

    public PayStatus(int code, String des, double amount) {
        this.code = code;
        this.des = des;
        this.amount = amount;
    }

}
