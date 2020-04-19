package com.lesson.source.pattern.bridge;

/**
 * @author zhengshijun
 * @version created on 2020/4/19.
 */
abstract class AbstractMessage {

    private final IMessage message;


    AbstractMessage(IMessage message) {
        this.message = message;
    }


    public void sendMessage(String toUser,String message) {
        this.message.sendMessage(toUser,message);
    }
}
