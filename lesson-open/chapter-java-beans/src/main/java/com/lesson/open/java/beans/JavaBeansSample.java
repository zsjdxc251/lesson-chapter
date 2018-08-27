package com.lesson.open.java.beans;

import org.springframework.beans.PropertyEditorRegistrySupport;
import org.springframework.beans.SimpleTypeConverter;

import javax.swing.plaf.synth.SynthSpinnerUI;
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.util.stream.Stream;

/**
 *
 *   Bean描述符（BeanDescriptor）
 *   方法描述符（MethodDescriptor）
 *   属性描述符（PropertyDescriptor）
 * @author zhengshijun
 * @version created on 2018/8/27.
 */
public class JavaBeansSample {

    public static void main(String[] args) throws Exception{


        Class<?> beanClass = Class.forName("com.lesson.open.java.beans.model.UserInfo");




        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass,  beanClass.getSuperclass());


        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        System.out.println(beanDescriptor);

        System.out.println("------------------------------------");

        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        Stream.of(methodDescriptors).forEach(System.out::println);

        System.out.println("------------------------------------");
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        Stream.of(propertyDescriptors).forEach(System.out::println);

        System.out.println("-------------------------------------");


        SimpleTypeConverter propertyEditorRegistrySupport = new SimpleTypeConverter();


        Object beanObject = beanClass.newInstance();
        Stream.of(propertyDescriptors).forEach(propertyDescriptor -> {

            if (propertyDescriptor.getName().equals("age")){
                PropertyEditor propertyEditor = propertyEditorRegistrySupport.getDefaultEditor(propertyDescriptor.getPropertyType());

                propertyEditor.addPropertyChangeListener(evt -> {

                   try {
                       propertyDescriptor.getWriteMethod().invoke(beanObject,PropertyEditor.class.cast(evt.getSource()).getValue());
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                });

                propertyEditor.setAsText("251");


//                System.out.println(propertyEditorRegistrySupport.getDefaultEditor(propertyDescriptor.getPropertyType()).getClass());
//                propertyDescriptor.setPropertyEditorClass(propertyEditorRegistrySupport.getDefaultEditor(propertyDescriptor.getPropertyType()).getClass());
//
//
//                PropertyEditor propertyEditor = propertyDescriptor.createPropertyEditor(beanObject);
//                propertyEditor.addPropertyChangeListener(evt -> {
//
//                   try {
//                       propertyDescriptor.getWriteMethod().invoke(beanObject,PropertyEditor.class.cast(evt.getSource()).getValue());
//                   } catch (Exception e) {
//                       e.printStackTrace();
//                   }
//                });
//                propertyEditor.setAsText("251");

            }


        });
        System.out.println(beanObject);


    }
}
