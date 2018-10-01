package com.lesson.source.pattern.command;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author zhengshijun
 * @version created on 2018/10/1.
 */
public class Macro {

    private final List<Command> commands;
    public Macro() {
        this.commands = Lists.newArrayList();
    }

    public void addCommand(Command command) {
        commands.add(command);
    }
    public void delCommand(Command command) {
        commands.remove(command);
    }

    public void execute(){
        commands.forEach(Command::execute);
    }
}
