package com.lesson.open.interfaces.design.chain;

/**
 * @author zhengshijun
 * @version created on 2018/9/26.
 */
public interface Filter {
    /**
     *
     * @param invoker
     * @return
     * @throws Exception
     */
    Result invoke(Invoker invoker) throws Exception;
}
