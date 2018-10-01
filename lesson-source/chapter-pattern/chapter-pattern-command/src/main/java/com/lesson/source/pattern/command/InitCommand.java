package com.lesson.source.pattern.command;

/**
 * @author zhengshijun
 * @version created on 2018/10/1.
 */
public class InitCommand extends DocumentCommand {

    public InitCommand(Document document) {
        super(document);
    }

    @Override
    public void execute() {
        document.init();
    }
}
