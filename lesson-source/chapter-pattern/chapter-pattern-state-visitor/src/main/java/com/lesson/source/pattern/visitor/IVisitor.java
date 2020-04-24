package com.lesson.source.pattern.visitor;

/**
 * @author zhengshijun
 * @version created on 2020/4/22.
 */
public interface IVisitor {

    void visit(Engineer engineer);


    void visit(Manager manager);
}
