package com.lesson.source.pattern.prototype.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Console;
import java.io.Serializable;

/**
 * @author zhengshijun
 * @version created on 2018/9/18.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Cloneable, Serializable {

    private String username;

    private Integer age;

    private String address;



    @Override
    public Person clone() throws CloneNotSupportedException {

        return (Person) super.clone();
    }
}
