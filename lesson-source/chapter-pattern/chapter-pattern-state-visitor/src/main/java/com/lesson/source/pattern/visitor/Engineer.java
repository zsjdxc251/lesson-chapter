package com.lesson.source.pattern.visitor;

import java.util.Random;

/**
 * @author zhengshijun
 * @version created on 2020/4/22.
 */
public class Engineer extends Employee {
    public Engineer(String name) {
        super(name);
    }

    @Override
    void accept(IVisitor visitor) {
        visitor.visit(this);
    }


    public int codeLine(){
        return new Random().nextInt(100);
    }
}
