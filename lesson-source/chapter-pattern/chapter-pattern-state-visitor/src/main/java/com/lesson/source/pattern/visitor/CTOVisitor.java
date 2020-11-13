package com.lesson.source.pattern.visitor;

/**
 * @author zhengshijun
 * @version created on 2020/4/22.
 */
public class CTOVisitor implements IVisitor{
    @Override
    public void visit(Engineer engineer) {

        System.out.println("员工："+engineer.getName()+",代码行数"+engineer.codeLine());

    }

    @Override
    public void visit(Manager manager) {
        System.out.println("员工："+manager.getName()+",产品数数"+manager.products());
    }
}
