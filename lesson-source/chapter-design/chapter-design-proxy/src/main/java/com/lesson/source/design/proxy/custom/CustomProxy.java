package com.lesson.source.design.proxy.custom;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Parameter;
import java.util.stream.Stream;

public class CustomProxy {


    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h) {


        String generateSource = generateSource(interfaces);

        System.out.println(generateSource);







        return null;
    }

    private static String generateSource(Class<?>[] interfaces){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("import java.lang.reflect.InvocationHandler;");
        stringBuilder.append("import java.lang.reflect.Method");
        stringBuilder.append("public class $Proxy0 implements ");
        for (int i=0;i<interfaces.length;i++) {
            stringBuilder.append(interfaces[0].getName());
            if (i != interfaces.length-1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("{");

        stringBuilder.append("private InvocationHandler handler;");

        stringBuilder.append("public $Proxy0(InvocationHandler handler){");
        stringBuilder.append("this.handler = handler;");
        stringBuilder.append("}");



        for (int i=0;i<interfaces.length;i++) {
            final Class<?> entry = interfaces[i];

            Stream.of(entry.getMethods()).forEach(method -> {

                stringBuilder.append("public "+method.getReturnType().getName()+" "+method.getName());
                stringBuilder.append("(");

                Parameter[] parameters = method.getParameters();

                Class<?>[] parameterClasses = method.getParameterTypes();

                for (int index = 0;index<parameterClasses.length; index++) {

                    stringBuilder.append(parameterClasses[index].getName()+" "+ parameters[index].getName());
                    if (index!= parameterClasses.length-1){
                        stringBuilder.append(",");
                    }
                }
                stringBuilder.append("){");



                stringBuilder.append("return handler.invoke(this, "+entry.getName()+".class.getMethod(\""+method.getName()+"\"," +
                        "new Class[]{");
                for (int index = 0;index<parameterClasses.length; index++) {

                    stringBuilder.append(parameterClasses[index].getName()+".class");
                    if (index!= parameterClasses.length-1){
                        stringBuilder.append(",");
                    }
                }
                stringBuilder.append("}), Object[] args)");



                stringBuilder.append("}");
            });





        }
        stringBuilder.append("}");


        return stringBuilder.toString();

    }
}
