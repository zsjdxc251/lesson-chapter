package com.lesson.source.pattern.strategy.payport;

import com.lesson.source.pattern.strategy.IPayment;
import com.lesson.source.pattern.strategy.PayStatus;

/**
 * @author zhengshijun
 * @version created on 2018/9/18.
 */
public enum PayType implements IPayment{

    /**
     *  支付类型
     */
    ALI_PAY(new AliPayment()),WECHAT_PAY(new WechatPayment()),UNION_PAY(new UnionPayment());

    private IPayment payment;

    PayType(IPayment payment) {
        this.payment = payment;
    }

    public IPayment get(){
        return payment;
    }

    @Override
    public PayStatus toPay(Integer id, double amount) {
        return payment.toPay(id,amount);
    }
}
