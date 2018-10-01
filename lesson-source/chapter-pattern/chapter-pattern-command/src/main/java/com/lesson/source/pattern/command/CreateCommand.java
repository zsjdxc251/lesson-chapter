package com.lesson.source.pattern.command;

/**
 * @author zhengshijun
 * @version created on 2018/10/1.
 */
public class CreateCommand extends DocumentCommand {

    public CreateCommand(Document document) {
        super(document);
    }

    @Override
    public void execute() {

        document.create();
    }
}
