package com.lesson.source.pattern.command;

/**
 * @author zhengshijun
 * @version created on 2018/10/1.
 */
public abstract class DocumentCommand implements Command{

    protected Document document;

    public DocumentCommand(Document document) {
        this.document = document;
    }




}
