package com.chapter.distributed.dubbo.provider;

/**
 *
 *  快速启动dubbo服务
 * @author zhengshijun
 * @version created on 2018/6/19.
 */
public class Bootstrap {

    public static void main(String[] args){

        // 默认加载META-INF.spring
       //默认情况下会使用spring容器来启动服务
        com.alibaba.dubbo.container.Main.main(
                new String[]{"spring"});
    }
}
