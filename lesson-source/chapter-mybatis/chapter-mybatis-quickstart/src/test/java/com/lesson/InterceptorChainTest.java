package com.lesson;

import com.lesson.source.mybatis.quickstart.plugins.CustomInterceptor;
import com.lesson.source.mybatis.quickstart.service.CityService;
import com.lesson.source.mybatis.quickstart.service.ICityService;
import org.apache.ibatis.plugin.InterceptorChain;

/**
 * @author zhengshijun
 * @version created on 2018/10/12.
 */
public class InterceptorChainTest {


    public static void main(String[] args) {

        InterceptorChain interceptorChain = new InterceptorChain();

        interceptorChain.addInterceptor(new CustomInterceptor());


        ICityService cityService = (ICityService)interceptorChain.pluginAll(new CityService());
        System.out.println(cityService);
        System.out.println(cityService.getName());

    }
}
