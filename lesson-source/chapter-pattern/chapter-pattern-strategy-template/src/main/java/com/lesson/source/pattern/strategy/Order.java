package com.lesson.source.pattern.strategy;

import com.lesson.source.pattern.strategy.payport.PayType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhengshijun
 * @version created on 2018/9/18.
 */
@Getter
@Setter
public class Order {

    private Integer id;

    private double amount;


    public Order(Integer id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    public PayStatus toPayment(PayType payType){
        return payType.toPay(id,amount);
    }

}
