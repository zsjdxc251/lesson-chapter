package com.lesson.source.pattern.command;

/**
 * @author zhengshijun
 * @version created on 2018/10/1.
 */


public class CommandSample {

    public static void main(String[] args){

        Document document = new Document();
        Macro macro = new Macro();
        macro.addCommand(new InitCommand(document));
        macro.addCommand(new OpenCommand(document));
        macro.addCommand(new CreateCommand(document));
        macro.addCommand(new CopyCommand(document));
        macro.addCommand(new CloseCommand(document));

        macro.execute();

    }
}
