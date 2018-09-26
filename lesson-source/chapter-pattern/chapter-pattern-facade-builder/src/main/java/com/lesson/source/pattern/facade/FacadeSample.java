package com.lesson.source.pattern.facade;

/**
 *   门面模式
 * @author zhengshijun
 * @version created on 2018/9/25.
 */
public class FacadeSample {

    public static void main(String[] args){

        UserService userService = new UserService();

        OrderService orderService = new OrderService();

        PaymentService paymentService = new PaymentService();

        // 如果不使用 设计模式调用是
        userService.checkUser();
        orderService.placeOrder();
        paymentService.doPay();

        // 使用设计模式
        FacadeShopService facadeShopService = new FacadeShopService();
        facadeShopService.doBuy();

    }
}
