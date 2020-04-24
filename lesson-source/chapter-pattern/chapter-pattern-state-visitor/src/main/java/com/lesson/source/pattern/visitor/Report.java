package com.lesson.source.pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2020/4/22.
 */
public class Report {

    List<Employee> employee = new ArrayList<>();

    {
        employee.add(new Manager("产品经理A"));
        employee.add(new Manager("产品经理B"));
        employee.add(new Manager("程序员A"));
        employee.add(new Manager("程序员B"));
        employee.add(new Manager("程序员C"));
    }

    public void execute(IVisitor visitor){

        employee.forEach(e->e.accept(visitor));

    }
}
