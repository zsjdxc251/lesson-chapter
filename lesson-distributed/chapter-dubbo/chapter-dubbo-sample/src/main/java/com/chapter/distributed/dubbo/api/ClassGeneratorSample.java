package com.chapter.distributed.dubbo.api;

import com.alibaba.dubbo.common.bytecode.ClassGenerator;

/**
 * @author zhengshijun
 * @version created on 2018/9/4.
 */
public class ClassGeneratorSample {


    public static void main(String[] args){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassGenerator cc = ClassGenerator.newInstance(classLoader);
        cc.setClassName("SampleEntry");
        cc.setSuperClass(BaseEntry.class);
        cc.addMethod("public String getName(){return \"zsjdxc251\";}");
        cc.addDefaultConstructor();

        try {
            Class<?> classes = cc.toClass();
            Object object = classes.newInstance();
            System.out.println(BaseEntry.class.cast(object).getName());;
        } catch (Exception e) {
            e.printStackTrace();
        }






    }
}
