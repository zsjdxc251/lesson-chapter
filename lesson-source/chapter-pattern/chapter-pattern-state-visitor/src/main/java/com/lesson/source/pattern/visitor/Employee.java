package com.lesson.source.pattern.visitor;

import lombok.Data;

import java.util.Random;

/**
 * @author zhengshijun
 * @version created on 2020/4/22.
 */
@Data
public abstract class Employee {

    private String name;

    private int kpi;


    public Employee(String name) {
        this.name = name;

        this.kpi = new Random().nextInt(100);
    }

    abstract void accept(IVisitor visitor);

}
