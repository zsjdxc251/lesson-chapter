package com.lesson.source.pattern.command;

/**
 * @author zhengshijun
 * @version created on 2018/10/1.
 */
public class CopyCommand extends DocumentCommand {

    public CopyCommand(Document document) {
        super(document);
    }

    @Override
    public void execute() {
        document.copy();
    }
}
