package com.lesson.open.interfaces.design.chain;

import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 *    filter 链条式调用
 *
 *    通过filter个数构建 filter 个 Invoker
 *    使用Invoker 封装一个 filter 且 filter 里面
 *
 *    last Invoker#invoke 调用的时候 会调用 filter#invoke
 *    而 filter 在调用的时候 把下一个 Invoker 传入进去
 *    调完filter#invoke 之后 再调用下一个 Invoker#invoke
 *
 * @author zhengshijun
 * @version created on 2018/9/26.
 */
public class FilterChainSample {

    public static void main(String[] args){


        List<Filter> filters = Lists.newArrayList();
        filters.add(new AuthorFilter());
        filters.add(new LoggerFilter());
        filters.add(new LoginFilter());
        filters.add(new OrderFilter());

        Invoker last = Result::new;

        for (int i = 0 ;i<filters.size() ;i++){
            Filter filter = filters.get(i);
            Invoker next = last;
            last = () -> {
                System.out.println("----");
                Result result = filter.invoke(next);
                result.increment();
                return result;
            };
        }

        try {
            Result result = last.invoke();
            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    static class LoggerFilter implements Filter {
        @Override
        public Result invoke(Invoker invoker) throws Exception {
            System.out.println("日志");
            return invoker.invoke();
        }
    }

    static class AuthorFilter implements Filter {
        @Override
        public Result invoke(Invoker invoker) throws Exception {
            System.out.println("授权");
            return invoker.invoke();
        }
    }
    static class LoginFilter implements Filter {
        @Override
        public Result invoke(Invoker invoker) throws Exception {
            System.out.println("登录");
            return invoker.invoke();
        }
    }
    static class OrderFilter implements Filter {
        @Override
        public Result invoke(Invoker invoker) throws Exception {
            System.out.println("订单");
            return invoker.invoke();
        }
    }



}
