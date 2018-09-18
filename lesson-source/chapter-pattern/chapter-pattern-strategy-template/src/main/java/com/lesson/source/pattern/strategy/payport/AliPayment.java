package com.lesson.source.pattern.strategy.payport;

import com.lesson.source.pattern.strategy.IPayment;
import com.lesson.source.pattern.strategy.PayStatus;

/**
 * @author zhengshijun
 * @version created on 2018/9/18.
 */
public class AliPayment implements IPayment {
    @Override
    public PayStatus toPay(Integer id, double amount) {
        return new PayStatus(200,"阿里支付",amount);
    }
}
