package com.lesson.source.pattern.visitor;

import java.util.Random;

/**
 * @author zhengshijun
 * @version created on 2020/4/22.
 */
public class Manager extends Employee{
    public Manager(String name) {
        super(name);
    }

    @Override
    void accept(IVisitor visitor) {

        visitor.visit(this);

    }

    public int products(){
        return new Random().nextInt(5);
    }
}
