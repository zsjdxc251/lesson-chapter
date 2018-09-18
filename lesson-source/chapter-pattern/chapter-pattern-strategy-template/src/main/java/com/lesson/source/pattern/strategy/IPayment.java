package com.lesson.source.pattern.strategy;

/**
 * @author zhengshijun
 * @version created on 2018/9/18.
 */
public interface IPayment {

    /**
     * 去执行支付
     *
     * @param id
     * @param amount
     * @return
     */
    PayStatus toPay(Integer id , double amount);
}
