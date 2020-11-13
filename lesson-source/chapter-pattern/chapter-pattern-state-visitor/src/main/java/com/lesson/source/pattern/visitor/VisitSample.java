package com.lesson.source.pattern.visitor;

/**
 * @author zhengshijun
 * @version created on 2020/4/22.
 */
public class VisitSample {

    public static void main(String[] args) {
        Report report = new Report();

        report.execute(new CEOVisitor());
    }
}
