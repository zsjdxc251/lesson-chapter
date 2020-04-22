package com.lesson.source.pattern.visitor;

/**
 * @author zhengshijun
 * @version created on 2020/4/22.
 */
public class CEOVisitor implements IVisitor{
    @Override
    public void visit(Engineer engineer) {

        System.out.println("员工："+engineer.getName()+",kip:"+engineer.getKpi());

    }

    @Override
    public void visit(Manager manager) {
        System.out.println("员工："+manager.getName()+",kpi:"+manager.getKpi());
    }
}
