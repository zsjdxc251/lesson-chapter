package com.lesson.source.pattern.delegate;

/**
 *
 *   委派模式 不是23中设计模式的一种
 *
 *   委派模式 是策略模式和代理模式的一种孵化
 *
 *   XXDispatcher
 *   XXDelegate
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class DelegateSample {

    public static void main(String[] args){


        TargetInvoker targetInvoker = new TargetInvoker();

        InvokerDelegate invokerDelegate = new InvokerDelegate(targetInvoker);

        invokerDelegate.doInvoker("这是该数据参数");


    }
}
