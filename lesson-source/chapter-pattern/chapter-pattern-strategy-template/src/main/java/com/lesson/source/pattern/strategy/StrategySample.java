package com.lesson.source.pattern.strategy;

import com.lesson.source.pattern.strategy.payport.AliPayment;
import com.lesson.source.pattern.strategy.payport.PayType;
import lombok.val;

/**
 * @author zhengshijun
 * @version created on 2018/9/18.
 */
public class StrategySample {

    public static void main(String[] args){


        val order = new Order(1,56.5);
        System.out.println(order.toPayment(PayType.WECHAT_PAY));


    }
}
