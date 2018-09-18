package com.lesson.source.pattern.prototype;

import com.lesson.source.pattern.prototype.model.Person;
import lombok.val;
import lombok.var;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

/**   {@link Cloneable}
 *    {@link org.apache.commons.beanutils.BeanUtils#copyProperties(Object, Object)}
 *    {@link org.springframework.beans.BeanUtils#copyProperties(Object, Object)}
 *    {@link org.springframework.cglib.beans.BeanCopier#create(Class, Class, boolean)}
 *    {@link net.sf.cglib.beans.BeanCopier#create(Class, Class, boolean)}
 *     深拷贝
 *    {@link SerializationUtils#clone(Serializable)}
 *
 *
 * @author zhengshijun
 * @version created on 2018/9/18.
 */
public class ProtoTypeSample {

    public static void main(String[] args) throws Exception{


        val person = new Person("mark",18,"深圳");

        var clonePerson = person.clone();

        var copyPerson = new Person();

        BeanCopier.create(Person.class,Person.class,true).copy(person,copyPerson,(value, target, context) -> {
            System.out.println(value+"-"+target+"-"+context);

            return null;
        });

        copyPerson = SerializationUtils.clone(person);

        System.out.println(copyPerson);



    }
}
