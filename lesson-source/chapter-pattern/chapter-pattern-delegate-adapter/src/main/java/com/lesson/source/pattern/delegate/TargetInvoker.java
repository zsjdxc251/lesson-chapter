package com.lesson.source.pattern.delegate;

/**
 * @author zhengshijun
 * @version created on 2018/9/23.
 */
public class TargetInvoker implements Invoker {
    @Override
    public void doInvoker(String params) {

        System.out.println("执行了该任务");
    }
}
