package com.lesson.source.pattern.facade;

/**
 * @author zhengshijun
 * @version created on 2018/9/26.
 */
public class FacadeShopService {

    private UserService userService ;

    private OrderService orderService ;

    private PaymentService paymentService;

    public FacadeShopService() {
        this.userService = new UserService();
        this.orderService = new OrderService();
        this.paymentService = new PaymentService();
    }


    public boolean doBuy(){
        boolean checkUser = userService.checkUser();
        boolean placeOrder = orderService.placeOrder();
        boolean doPay = paymentService.doPay();
        return checkUser&&placeOrder&&doPay;
    }
}
