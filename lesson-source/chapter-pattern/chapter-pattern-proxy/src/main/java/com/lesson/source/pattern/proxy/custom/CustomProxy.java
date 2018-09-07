package com.lesson.source.pattern.proxy.custom;

import com.alibaba.dubbo.common.bytecode.ClassGenerator;
import com.alibaba.dubbo.common.compiler.support.JdkCompiler;
import org.apache.commons.lang3.ClassUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Locale;
import java.util.stream.Stream;

public class CustomProxy {


    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h) {


        String generateSource = generateSource(interfaces);

        ClassGenerator cg = ClassGenerator.newInstance(loader);
        cg.setClassName("$Proxy0");
        Stream.of(interfaces).forEach(cg::addInterface);

        cg.addField("arg0",Modifier.PRIVATE,h.getClass());

        cg.addConstructor(Modifier.PUBLIC,new Class[]{h.getClass()},"this.arg0=arg0");


        Stream.of(interfaces).forEach(interfaceEntry->{

            Stream.of(interfaceEntry.getMethods()).forEach(method->{





                cg.addMethod(method.getName(),method.getModifiers(),method.getReturnType(),method.getParameterTypes(),"");







            });
        });




        return null;
    }



    private static String generateSource(Class<?>[] interfaces){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("package com.lesson.source.design.proxy.custom;");
        stringBuilder.append("import java.lang.reflect.InvocationHandler;");
        stringBuilder.append("import java.lang.reflect.Method;");
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

                Class<?> returnType = method.getReturnType();

                stringBuilder.append("@Override public "+method.getReturnType().getName()+" "+method.getName());
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

                boolean isReturn = true;
                if (returnType == void.class){
                    isReturn = false;
                }


                if (isReturn){
                    stringBuilder.append(method.getReturnType().getName()).append(" result = ");
                    if (returnType.isPrimitive()){
                        stringBuilder.append("0");
                    } else {
                        stringBuilder.append("null");
                    }
                    stringBuilder.append(";");
                }

                stringBuilder.append(" try {");
                if (isReturn){
                    stringBuilder.append("result =  ("+method.getReturnType().getName()+")");
                }
                stringBuilder.append("handler.invoke(this, "+entry.getName()+".class.getMethod(\""+method.getName()+"\"" +
                        "");
                if (parameterClasses.length > 0){
                    stringBuilder.append(",");
                }
                for (int index = 0;index<parameterClasses.length; index++) {

                    stringBuilder.append(parameterClasses[index].getName()+".class");
                    if (index!= parameterClasses.length-1){
                        stringBuilder.append(",");
                    }
                }
                stringBuilder.append("),");

                stringBuilder.append("new Object[]{");
                for (int index = 0;index<parameters.length; index++) {
                    stringBuilder.append(parameters[index].getName());
                    if (index!= parameters.length-1){
                        stringBuilder.append(",");
                    }
                }
                stringBuilder.append("});");
                stringBuilder.append("} catch(Throwable e){ e.printStackTrace();}");
                if (isReturn){
                    stringBuilder.append("return result;");
                }
                stringBuilder.append("}");
            });

        }
        stringBuilder.append("}");


        return stringBuilder.toString();

    }
}
